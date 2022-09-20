package com.mduran.springboot.config;

import com.mduran.springboot.service.WikiMediaChangesProducer;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ComponentScan(basePackageClasses = WikiMediaChangesProducer.class)
public class KafkaTopicConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(KafkaTopicConfig.class);

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("wikimedia_recentchange").build(); // .partitions(n) should you choose
    }

    // This is one way to do it, but instantiating a bean
    // but what about automatically scanning for it and then retrieving it from the appContext

//    @Bean
//    public WikiMediaChangesProducer wikiMediaChangesProducer() {
//        LOGGER.info("wikiMediaChangesProducer bean instantiated");
//
//        return new WikiMediaChangesProducer();
//        // other error checking here
//
//
//    }


}
