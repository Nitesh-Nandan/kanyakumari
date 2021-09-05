package org.kanyakumari.web.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public boolean sendSync(String topic, String message) {
        ListenableFuture<SendResult<String, String>>  future = kafkaTemplate.send(topic, message);
        try {
            SendResult<String, String> res = future.get(1000, TimeUnit.MILLISECONDS);
            System.out.println("Hold");
        } catch (Exception ex) {
            log.error("Error Occurred {}", ex);
            return false;
        }
        return true;
    }

    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }


    @Retryable(value = {RetryException.class}, backoff = @Backoff(delay = 500, multiplier = 5))
    public boolean sendWithRetry(String topic, String message) {
        ListenableFuture<SendResult<String, String>>  future = kafkaTemplate.send(topic, message);
        try {
           future.get(1000, TimeUnit.MILLISECONDS);
        } catch (Exception ex) {
            log.error("Error occurred {} ", ex);
            throw new RetryException(ex.getMessage());
        }
        return true;
    }

    @Recover
    public boolean recover(RetryException ex) {
        log.error("Unable to Recover {}", ex);
        return false;
    }

}
