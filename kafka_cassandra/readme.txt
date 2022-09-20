to have this app running you need to have kafka up and runnning (I downloaded it locally
and then started the below zookeeper and kafka server scripts)

1) ~/Desktop/github_repos/kafka_work/kafka
    zookeeper:
        - ~/Desktop/github_repos/kafka_work/kafka $ ./bin/zookeeper-server-start.sh ./config/zookeeper.properties
    kafka:
        - ~/Desktop/github_repos/kafka_work/kafka $  bin/kafka-server-start.sh config/server.properties

2) then if you want to see the data that has been stored in the topic you do this
    - bin/kafka-console-consumer.sh --topic wikimedia_recentchange --from-beginning --bootstrap-server localhost:9092

    this will allow you to see all of the events that have been sent to the server

3) if you run both of the modules at the same time then you can see the message being sent
   and the message downloaded from the kafka broker's topic

4) next we will need to set up a database

    - tutorial does MySQL (which I didnt do) [DISREGARD]
        - whenever you want to connect to a MySQL db you need
            - Spring Data JPA
            - MySQL Driver

    - I focussed on hooking into a Cassandra Instance
        - make sure that cassandra is running on port 9042
            - docker run -p 9042:9042 cassandra:latest --> expose the port globally, and starts server
            - docker exec -it 73e0f2b524d1 cqlsh --> allows me to get into the globally exposed DB and then create the tables

                - above command allows me into the keyspace. now do the next steps
                    - make sure the following is created in the keyspace
                        - CREATE KEYSPACE wikimedia;
                        - USE wikimedia;
                        - // CREATE TYPE metadataUDT (uri varchar, request_id varchar, topic varchar, dt varchar);
                            - disregard this statement, and the part of the table creation below (couldnt get to work)
                        - CREATE TABLE wikimediaevent_recentchange (
                                       name varchar primary KEY,
                                       wiki_event_data varchar,
                                       web_url varchar,
                                       // entityMetadata frozen<metadataUDT> [DISREGARD Right Now]
                                       );


5) Now Run both programs, and see the producer send messages to kafka, and the consumer
   receive them and write to cassandra


Good Sources:
1) Java Guides Youtube Tutorial -- Spring Boot and Apache kafka Tutorial (24 series)
2) Medium -- spring data for cassandra a complete example
3) baeldung -- Sprind data cassandra tutorial