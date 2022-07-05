package com.votingsystem.VotingSystem.producers;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Component;

import java.util.Properties;

@EnableKafka
@Component
public class VotingResultProducer {
    @Value("${kafka.topic.voting-result}")
    private String TOPIC;

    @Value("${kafka.bootstrap.servers}")
    private String BOOTSTRAP_SERVERS;

    private Producer<String, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        return new KafkaProducer<String, String>(props);
    }

    public void runProducer(String agendaId, String message) throws Exception {
        final Producer<String, String> producer = createProducer();

        final ProducerRecord<String, String> record =
                new ProducerRecord<>(TOPIC, agendaId, message);

        try {
            producer.send(record).get();
        } catch (Exception e) {
            throw new Exception("Error sending message", e);
        } finally {
            producer.flush();
            producer.close();
        }

        producer.flush();
        producer.close();
    }
}
