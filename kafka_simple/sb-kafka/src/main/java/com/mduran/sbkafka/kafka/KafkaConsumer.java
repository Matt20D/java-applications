package com.mduran.sbkafka.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    // now we are subscribed to the topic, and then can process the data
    @KafkaListener(topics = "${mykafka.topicname}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        LOGGER.info("=========== In LISTENER ===========");
        LOGGER.info(String.format("MESSAGE RECEIVED <======== %s", message));
        LOGGER.info("===================================");
        // Do some other stuff here
    }
}
