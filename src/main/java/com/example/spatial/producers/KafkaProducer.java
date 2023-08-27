package com.example.spatial.producers;

import com.example.spatial.model.LatLong;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.LatLng;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;
    private final String topicName = "my-topic";

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void produceMessage(LatLong latLng) throws JsonProcessingException {
        for (int i = 0; i < 10000; i++) {

            String jsonString = objectMapper.writeValueAsString(latLng);
            kafkaTemplate.send(topicName, jsonString);
            System.out.println("Produced message: " + latLng.toString());
        }
        System.out.println("********************");
    }

}
