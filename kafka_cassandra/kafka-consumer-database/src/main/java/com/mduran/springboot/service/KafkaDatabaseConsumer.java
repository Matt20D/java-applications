package com.mduran.springboot.service;

//import com.mduran.springboot.entity.WikimediaEventData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mduran.springboot.entity.MetadataUDT;
import com.mduran.springboot.entity.WikimediaEventData;
import com.mduran.springboot.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private WikimediaDataRepository dataRepository;

    // constructor based injection
    // don't need to add @Autowired, because there is only one constructor in this class
    public KafkaDatabaseConsumer(WikimediaDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(topics = "wikimedia_recentchange", groupId = "myGroup")
    public void consume(String eventMessage) {
        LOGGER.info(String.format("EVENT MESSAGE RECEIVED <=========== %s", eventMessage));

        // convert JSON string to Map
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;

        try {
            map = mapper.readValue(eventMessage, Map.class);

            // just so I remember which keys are in the set
            // System.out.println(map.keySet());

            // extract the params of interest
            String uname = (String) map.get("user");
            String logComment = (String) map.get("comment");
            String userUrl = (String) map.get("server_url");

            Map<String, Object> metaDataMap =  (Map<String, Object>)  map.get("meta");


            // just so I remember which keys are in the set
            // System.out.println(metaDataMap.keySet());

            // lets create a Meta Data User Defined Types to also save into Cassandra
            // Cassandra supports UDTs
            MetadataUDT mdObj = new MetadataUDT(
                                                    (String) metaDataMap.get("uri"),
                                                    (String) metaDataMap.get("request_id"),
                                                    (String) metaDataMap.get("topic"),
                                                    (String) metaDataMap.get("dt")
                                                );

            System.out.println("created the UDT");
            // now we are going to save the data.
            // usually you would use setters and builders here in order to ensure that
            // everything is truly immutable
            WikimediaEventData wikimediaEventData = WikimediaEventData
                                                        .newBuilder()
                                                        .withWikiEventData(logComment)
                                                        .withName(uname)
                                                        .withWebUrl(userUrl)
                                                        //.withUDT(null)
                                                        .build();


            // save to cassandra DB
            dataRepository.save(wikimediaEventData);

            LOGGER.info(String.format("SAVED OBJECT CASSANDRA ===========> %s", wikimediaEventData.toString()));

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }



    }
}
