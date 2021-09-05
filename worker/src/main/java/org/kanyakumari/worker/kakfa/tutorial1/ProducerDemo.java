package org.kanyakumari.worker.kakfa.tutorial1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {
    public static void main(String[] args) {
        // Properties

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create Producer
        KafkaProducer <String, String> producer = new KafkaProducer<>(properties);

        // Producer Record
        ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "hello world");

        // send Data
        producer.send(record);

        //flush data
        producer.flush();

        // flush and close
        producer.close();

        System.out.println("Hello world");
    }
}
