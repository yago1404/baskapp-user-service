package com.baskapp.baskappsocial.infra.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/status")
    @ResponseBody
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("online");
    }
}
