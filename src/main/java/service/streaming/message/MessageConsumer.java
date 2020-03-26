package service.streaming.message;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import util.Configs;
import util.kafka.KafkaUtil;
import util.redis.RedisUtil;

import java.util.Arrays;

//import util.hbase.HbaseUtil;

@Component
public class MessageConsumer {

    private static KafkaConsumer<String, String> consumer = KafkaUtil.getConsumer();

    static {
        consumer.subscribe(Arrays.asList(Configs.KAFKA_TOPIC));
    }

    private Jedis jedis = RedisUtil.getConnection();

    @Scheduled(initialDelay = 2000, fixedRate = 10000)
    private void consume() {
        ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
        Pipeline pipeline = jedis.pipelined();
        System.out.println("定时消费数据");
        for (ConsumerRecord<String, String> record : consumerRecords) {
            String value = "unknown";
            try {
                value = record.value();
//                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), value);
//                System.out.println("key: " + value + " value: " + jedis.incr(value));
                pipeline.incr(value);
//                jedis.incr(value);
//                HbaseUtil.insertData(Configs.HBASE_TABLE_NAME, Configs.HBASE_FAMILY_NAME, Configs.HBASE_QUALIFIER, value, String.valueOf(1));
            } catch (Exception e) {
                System.err.println("something went wrong at item " + value);
                e.printStackTrace();
                return;
            }
        }
        pipeline.sync();
    }
}

