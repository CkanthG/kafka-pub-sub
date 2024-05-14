package com.sree.controller;

import com.sree.dto.User;
import com.sree.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publisher")
public class EventController {

    @Autowired
    KafkaProducerService kafkaProducerService;

    @PostMapping("/publish")
    public ResponseEntity<?> publishUserMessage(@RequestBody User user) {
        try {
            kafkaProducerService.sendUserMessage(user);
            return ResponseEntity.ok("Data Sent Successfully !!");
        } catch (Exception exe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
