package service.streaming.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import util.Configs;
import util.kafka.KafkaUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class CSVLoader extends BaseLoader {

    private static String[] filePaths = new String[]{
            "D:\\study\\大四上\\毕设\\文件\\csv\\BX-CSV-Dump\\BX-Users.csv", // true
            "D:\\study\\大四上\\毕设\\文件\\csv\\BX-CSV-Dump\\BX-Books.csv", // true
            "D:\\study\\大四上\\毕设\\文件\\csv\\steam-video-games\\steam-200k.csv", // false
            "D:\\study\\大四上\\毕设\\文件\\csv\\BX-CSV-Dump\\books.csv", // true
    };

    private static Boolean[] hasHeaders = new Boolean[]{true, true, false, true};

    private List<String> list = Arrays.asList("Significance (Significance, #1)",
            "Accordance (Significance, #2)", "My Brilliant Friend (The Neapolitan Novels #1)",
            "Where I Belong (Alabama Summer, #1)", "Confess", "I Capture the Castle",
            "Timequake", "Assassin's Quest (Farseer Trilogy, #3)",
            "The Billionaire's Obsession ~ Simon (The Billionaire's Obsession, #1)", "God Bless You, Mr. Rosewater",
            "Let It Snow: Three Holiday Romances", "Wait for It", "Pet Sematary", "The Chemist", "Underground Airlines",
            "Egomaniac", "Archer's Voice", "Carry On", "The Zookeeper's Wife", "The Hate U Give", "Love Warrior",
            "Hogwarts: An Incomplete and Unreliable Guide (Pottermore Presents, #3)",
            "Short Stories from Hogwarts of Heroism, Hardship and Dangerous Hobbies (Pottermore Presents, #1)",
            "Short Stories from Hogwarts of Power, Politics and Pesky Poltergeists (Pottermore Presents, #2)",
            "Hollowland (The Hollows, #1)", "Bossman", "Vicious (Sinners of Saint, #1)",
            "Norse Mythology", "The Underground Railroad");

    private KafkaProducer<String, String> kafkaProducer = KafkaUtil.getProducer();

    public CSVLoader() {
        super();
    }

    @Override
    public boolean read() {
        Reader reader;
        int index = 3;
        boolean success = false;
        try {
            reader = new FileReader(filePaths[index]);
            CSVParser csvRecords;
            if (hasHeaders[index]) {
                csvRecords = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            } else {
                csvRecords = CSVFormat.RFC4180.parse(reader);
            }
            for (CSVRecord csvRecord : csvRecords) {
                System.out.println("title: " + csvRecord.get("title") + " ratings_count: " + csvRecord.get("ratings_count"));
            }
            csvRecords.close();
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File " + filePaths[index] + " not found");  //
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            success = true;
        }
        return success;
    }

    @Override
    public boolean write(String filePath) {
        if (null == array) {
            throw new NullPointerException("Please read or add data first");
        }
        return false;
    }

    @Scheduled(initialDelay = 5000, fixedRate = 5000)
    private void sendMessage() {
        System.out.println("定时生产数据");
        Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            kafkaProducer.send(new ProducerRecord<>(Configs.KAFKA_TOPIC, list.get(random.nextInt(list.size()))));
        }
    }
}
