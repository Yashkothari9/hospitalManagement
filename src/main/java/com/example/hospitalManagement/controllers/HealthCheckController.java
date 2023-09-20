package com.example.hospitalManagement.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/p/health")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<Map<String,String>> checkHealth() {
        Map<String,String> health = new HashMap<>();
        health.put("health","Ok");
        return ResponseEntity.ok(health);
    }
}
