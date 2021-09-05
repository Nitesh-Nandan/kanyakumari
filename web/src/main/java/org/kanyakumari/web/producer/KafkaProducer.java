package org.kanyakumari.web.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public boolean send(String topic, String message) {
        kafkaTemplate.send(topic, message);
        return true;
    }

}
