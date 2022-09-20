package com.mduran.sbkafka.controller;

import com.mduran.sbkafka.kafka.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // spring Rest MVC
@RequestMapping("/api/v1/kafka")
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaProducer kafkaProducer;

    // if spring bean only takes one paremtrized constructor then I dont need the
    // @Autowired annotation here. If I had multiple constructors for this class
    // then yes I would need @Autowired.
    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    // http://localhost:8080/api/v1/kafka/publish?message=hello%20world
    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);
        LOGGER.info(String.format("I just sent %s =====> to the topic", message));
        return ResponseEntity.ok(String.format("I just sent %s =====> to the topic", message));
    }
}
