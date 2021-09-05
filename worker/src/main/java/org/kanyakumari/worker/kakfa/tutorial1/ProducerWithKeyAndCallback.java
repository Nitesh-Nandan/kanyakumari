package org.kanyakumari.worker.kakfa.tutorial1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerWithKeyAndCallback {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Properties

        Logger log = LoggerFactory.getLogger(ProducerWithKeyAndCallback.class);

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // Producer Record
        for (int i = 0; i < 10; i++) {
            String topic = "first_topic";
            String value = "hello world " + i;
            String key = "id_" + i;

            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

            log.info("Key: " + key);

            // send Data
            producer.send(record, (recordMetadata, e) -> {
                if (e == null) {
                    log.info("Received new MetaData. \n" +
                            "Topic: " + recordMetadata.topic() + "\n" +
                            "Partition: " + recordMetadata.partition() + "\n" +
                            "Offset: " + recordMetadata.offset() + "\n" +
                            "Timestamp: " + recordMetadata.timestamp());
                } else {
                    log.error("Error with Producing", e);
                }
            }).get(); // Don't do in production
        }

        // flush and close
        producer.close();

        System.out.println("Hello world");
    }
}
