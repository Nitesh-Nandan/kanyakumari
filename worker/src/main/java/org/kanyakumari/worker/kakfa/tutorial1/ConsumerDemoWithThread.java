package org.kanyakumari.worker.kakfa.tutorial1;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

class ConsumerThread implements Runnable {

    private CountDownLatch latch;
    private  KafkaConsumer<String, String> consumer;
    Logger log = LoggerFactory.getLogger(ConsumerThread.class.getName());

    public ConsumerThread(CountDownLatch latch, Properties  properties, String topic) {
        this.latch = latch;
        this.consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                records.forEach((record) -> {
                    log.info("Key: " + record.key() + ", Value: " + record.value());
                    log.info("Partition: " + record.partition() + ", Offset: " + record.offset());
                });
            }
        } catch (WakeupException ex) {
            log.error("Received shutdown signal!!");
        } finally {
            consumer.close();
            latch.countDown();
        }

    }
    public void shutdown() {
        consumer.wakeup();
    }
}


public class ConsumerDemoWithThread {
    Logger log = LoggerFactory.getLogger(ConsumerDemoWithThread.class.getName());
    public static void main(String[] args) {
        new ConsumerDemoWithThread().run();
    }

    public void run() {
        final String bootStrapServer = "127.0.0.1:9092";
        final String groupId = "my-fourth-application";
        final String topic = "first_topic";

        // create consumer config
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        CountDownLatch latch = new CountDownLatch(1);

        Runnable myConsumerThread = new ConsumerThread(latch, properties, topic);

        Thread thread = new Thread(myConsumerThread);
        thread.start();

        // add a shutdown hook

        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            log.info("Caught the shutdown hook");
            ((ConsumerThread)myConsumerThread).shutdown();
            try {
                latch.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            log.info("Application exited");
        }));

        try {
            latch.await();
        } catch (InterruptedException e) {
            log.info("Thread is interrupted ", e);
            e.printStackTrace();
        } finally {
            log.info("Application is closing");
        }
    }
}
