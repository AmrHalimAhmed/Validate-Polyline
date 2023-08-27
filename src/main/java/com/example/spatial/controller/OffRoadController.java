package com.example.spatial.controller;

import com.example.spatial.model.LatLong;
import com.example.spatial.producers.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class OffRoadController {

    private KafkaProducer kafkaProducer;

    public OffRoadController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/add")
    ResponseEntity<?> validateOffRoad(@RequestBody LatLong latLong) throws JsonProcessingException {
        kafkaProducer.produceMessage(latLong);
        return ResponseEntity.ok().build();
    }
}