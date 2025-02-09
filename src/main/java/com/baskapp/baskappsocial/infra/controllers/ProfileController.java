package com.baskapp.baskappsocial.infra.controllers;

import com.baskapp.baskappsocial.data.dtos.response.ResponseBodyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyDto<String>> getProfile(@PathVariable String id) {
        return ResponseEntity.ok().body(new ResponseBodyDto<>("success", 200, id));
    }
}
