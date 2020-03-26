package util.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.TopicPartition;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import scala.Tuple2;
import util.Configs;

import java.util.*;

public class KafkaUtil {
    private static AdminClient adminClient;
    private static Properties properties;
    // kafka相关参数
    private static HashMap<String, Object> producerParams = new HashMap<>();
    private static HashMap<String, Object> consumerParams = new HashMap<>();

    static {
        properties = new Properties();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, Configs.KAFKA_BROKERS);
        // producer
        producerParams.put("bootstrap.servers", Configs.KAFKA_BROKERS);
        producerParams.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerParams.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // consumer
        consumerParams.put("bootstrap.servers", Configs.KAFKA_BROKERS);
        consumerParams.put("group.id", Configs.KAFKA_CONSUMER_GROUP);
        consumerParams.put("auto.offset.reset", "latest");
//        consumerParams.put("auto.offset.reset", "earliest");
        consumerParams.put("auto.commit.interval.ms", "1000");
        consumerParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerParams.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        consumerParams.put("metadata.broker.list", Configs.KAFKA_BROKERS);
//        consumerParams.put("bootstrap.servers", Configs.KAFKA_BROKERS);
//        consumerParams.put("group.id", Configs.KAFKA_CONSUMER_GROUP);
//        consumerParams.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        consumerParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        consumerParams.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }

    private KafkaUtil() {}

    public static void createTopicIfNotExist(String topic) {
        try {
            adminClient = AdminClient.create(properties);
            if(null != adminClient.describeTopics(Arrays.asList(topic))) {
                System.out.println("主题 " + topic + " 已存在");
                return;
            }
            NewTopic newTopic = new NewTopic(topic, 2, (short) 2);
            CreateTopicsResult createTopicsResult = adminClient.createTopics(Arrays.asList(newTopic));
            createTopicsResult.all().get();
            adminClient.close();
            System.out.println("创建主题成功：" + topic);
        } catch (Exception e) {
            System.err.println("创建主题 " + topic + " 失败");
            e.printStackTrace();
        }
    }

    public static void deleteTopic(String topic) {
        try {
            adminClient = AdminClient.create(properties);
            DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Arrays.asList(topic));
            deleteTopicsResult.all().get();
            adminClient.close();
            System.out.println("创建主题成功：" + topic);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Deprecated
    public static ArrayList<String> getMessage(String appName) throws InterruptedException {
        SparkConf conf = new SparkConf().setMaster(Configs.KAFKA_MASTER).setAppName(appName);
        JavaSparkContext sc = new JavaSparkContext(conf);
        sc.setLogLevel("WARN");
        JavaStreamingContext ssc = new JavaStreamingContext(sc, Durations.seconds(1));

        Collection<String> topicsSet = new HashSet<>(Arrays.asList(Configs.KAFKA_TOPIC));
        // Topic分区  也可以通过配置项实现
        // 如果没有初始化偏移量或者当前的偏移量不存在任何服务器上，可以使用这个配置属性
        // earliest 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
        // latest 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
        // none topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
        // producerParams.put("auto.offset.reset", "latest");
        // producerParams.put("enable.auto.commit",false);

        Map<TopicPartition, Long> offsets = new HashMap<>();
        offsets.put(new TopicPartition(Configs.KAFKA_TOPIC, 0), 2L);
        //通过KafkaUtils.createDirectStream(...)获得kafka数据，kafka相关参数由producerParams指定
        JavaInputDStream<ConsumerRecord<Object, Object>> lines = KafkaUtils.createDirectStream(
                ssc,
                LocationStrategies.PreferConsistent(),
                ConsumerStrategies.Subscribe(topicsSet, producerParams, offsets)
        );
        //这里就跟之前的demo一样了，只是需要注意这边的lines里的参数本身是个ConsumerRecord对象
        JavaPairDStream<String, Integer> counts =
                lines.flatMap(x -> Arrays.asList(x.value().toString().split(" ")).iterator())
                        .mapToPair(x -> new Tuple2<String, Integer>(x, 1))
                        .reduceByKey((x, y) -> x + y);
        counts.print();
        // 可以打印所有信息，看下ConsumerRecord的结构
        // TODO: 测试并修改返回值
        ArrayList<String> arrayList = new ArrayList<>();
        lines.foreachRDD(rdd -> {
            rdd.foreach(x -> {
                System.out.println(x);
                System.out.println("key: " + x.key().toString());
                System.out.println("value: " + x.value().toString());
                arrayList.add(x.value().toString());
            });
        });
        ssc.start();
        ssc.awaitTermination();
        ssc.close();
        return arrayList;
    }

    public static KafkaProducer<String, String> getProducer() {
        return new KafkaProducer<>(producerParams);
    }

    public static KafkaConsumer<String, String> getConsumer() {
        return new KafkaConsumer<>(consumerParams);
    }
}