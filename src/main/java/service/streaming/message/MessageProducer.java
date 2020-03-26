package service.streaming.message;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import util.Configs;
import util.kafka.KafkaUtil;

import java.util.Random;

public class MessageProducer {
    public void start() {
        SparkConf conf = new SparkConf().setMaster(Configs.KAFKA_MASTER).setAppName("count");
        JavaSparkContext sc = new JavaSparkContext(conf);
        sc.setLogLevel("WARN");
        KafkaProducer<String, String> producer = KafkaUtil.getProducer();
        new Thread(new MessageSender(producer)).start();
    }
}

class MessageSender implements Runnable {

    private static String[] products = new String[]{"item1", "item2", "item3"};
    private KafkaProducer<String, String> producer;
    private int number_of_items;

    MessageSender(KafkaProducer<String, String> producer) {
        this.producer = producer;
        this.number_of_items = 10;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Random random = new Random();
        String msg;
        while (number_of_items-- > 0) {
            msg = products[random.nextInt(3)];
            System.out.println("Sending message: " + msg);
            producer.send(new ProducerRecord<>(Configs.KAFKA_TOPIC, msg));
        }
    }
}