package com.mduran.sbkafka.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/*
    recall:
        @Component - mark the beans as spring's managed components, will pick up and register this bean with this annotation
        @Service - these beans handle business logic, and indicate they are to be used within the service layer
        @Repository - these beans handle data persistence. Not automatically scanned!
 */
@Service // hold business logic in service layer
public class KafkaProducer {

    @Value("${mykafka.topicname}")
    private String TOPIC_NAME;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    // constructor dependency to inject the kafkaTemplate bean into this kafkaProducer class
    // springboot will auto configure this for us.
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        LOGGER.info("Injecting KafkaTemplate<> dependency into this class");
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * uses the kafka template to send @message to @TOPIC_NAME
     */
    public void sendMessage(String message) {
        LOGGER.info(String.format("Message sent %s", message));
        kafkaTemplate.send(TOPIC_NAME, message);
    }
}
