package com.mduran.sbkafka.kafka;

import com.mduran.sbkafka.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    private static Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "${mykafka.topicnamejson}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(User user) {
        LOGGER.info("=========== In JSON LISTENER ===========");
        LOGGER.info(String.format("JSON MESSAGE RECEIVED <======= %s", user.toString()));
        LOGGER.info("========================================");
    }
}
