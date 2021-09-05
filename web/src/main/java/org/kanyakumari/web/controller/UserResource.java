package org.kanyakumari.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.kanyakumari.web.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("kafka")
public class UserResource {

    @Autowired
    private KafkaProducer producer;
    private static final String TOPIC = "kafka_example";

    @GetMapping("/publish/{message}")
    public String post(@PathVariable("message") final String message) {
        log.info("Message: {}", message);
        producer.send(TOPIC, message);
        return "Published Successfully";
    }

}
