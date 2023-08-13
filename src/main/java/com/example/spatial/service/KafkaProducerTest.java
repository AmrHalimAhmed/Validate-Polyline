//package com.example.spatial.service;
//
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaProducerTest {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    private final String topicName = "test-topic";
//
//    public KafkaProducerTest(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void produceMessage(String message) {
//        kafkaTemplate.send(topicName, message);
//        System.out.println("Produced message: " + message);
//    }
//
//}
