This is a basic app that is configured to integrate a Spring Boot app with Kafka via Spring-Kafka Dependency

1) get kafka and zookeeper up and running
    - ~/Desktop/github_repos/kafka_work/kafka $  bin/kafka-server-start.sh config/server.properties
    - ~/Desktop/github_repos/kafka_work/kafka $ ./bin/zookeeper-server-start.sh ./config/zookeeper.properties