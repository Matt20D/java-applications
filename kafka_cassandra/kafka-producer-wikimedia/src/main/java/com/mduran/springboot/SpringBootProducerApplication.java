package com.mduran.springboot;

import com.mduran.springboot.config.KafkaTopicConfig;
import com.mduran.springboot.service.WikiMediaChangesProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;



@SpringBootApplication
@Import({KafkaTopicConfig.class}) // want to get the wikiMediaChangesProducer beans
public class SpringBootProducerApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootProducerApplication.class);


    public static void main(String[] args) {

        SpringApplication.run(SpringBootProducerApplication.class);
    }


    //    @Autowired // inject this producer into this main method.
    private WikiMediaChangesProducer wikiMediaChangesProducer;

    private ApplicationContext ctx;

    @Autowired // setter injected from the App Context
    public void setApplicationContext(ApplicationContext context) {
        this.ctx=context;
        // use the context in order to instantiate the media changes bean
        this.wikiMediaChangesProducer = this.ctx.getBean(WikiMediaChangesProducer.class);
    }

    //
    // In this override run method we will be probing the event source for data
    // and then sending messages to the kafka broker
    //
    @Override // String... args is an array of params of type String
              // whereas String[] is a single param (but essentially same purpose)
    public void run(String... args) throws Exception {

        wikiMediaChangesProducer.sendMessage();
    }
}
