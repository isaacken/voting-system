package com.votingsystem.VotingSystem.producers;

import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
public class VotingResultProducer {
    private final static String TOPIC = "voting-results-topic";
    private final static String BOOTSTRAP_SERVERS = System.getenv("KAFKA_BOOTSTRAP_SERVERS");


}
