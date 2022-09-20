package com.mduran.sbkafka.kafka;

import com.mduran.sbkafka.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger((JsonKafkaProducer.class));

    @Value("${mykafka.topicname}-json")
    private String MY_TOPIC_NAME;

    private KafkaTemplate<String, User> kafkaTemplate;

    // Do not need @Autowired here
    public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // the JsonSerializer that we are putting in the application.properties allows us to serialize
    // this User obkect as a Json Byte[]
    public void sendMessage(User data) {

        Message<User> message = MessageBuilder
                                    .withPayload(data)
                                    .setHeader(KafkaHeaders.TOPIC, MY_TOPIC_NAME)
                                    .build();

        kafkaTemplate.send(message);

        LOGGER.info("Just sent serialized object ====> " + data.toString());
    }
}
