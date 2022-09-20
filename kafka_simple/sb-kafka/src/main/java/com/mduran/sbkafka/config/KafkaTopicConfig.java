package com.mduran.sbkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

// we will need to configure a spring bean to configure the Kafka Topics
@Configuration
public class KafkaTopicConfig {

    @Value("${mykafka.topicname}")
    private String TOPIC_NAME;

    @Value("${mykafka.topicnamejson}")
    private String TOPIC_NAME_JSON;

    // kafka topic within a kafka cluster in Spring Boot App
    @Bean
    public NewTopic javaGuidesTopic() {
        return TopicBuilder.name(TOPIC_NAME).build(); // we could also add in a number of partitions
                                                              // to the topic using .partitions(n) method
    }

    // kafka topic within a kafka cluster in Spring Boot App
    @Bean
    public NewTopic javaGuidesTopicJson() {
        return TopicBuilder.name(TOPIC_NAME_JSON).build(); // we could also add in a number of partitions
        // to the topic using .partitions(n) method
    }
}
