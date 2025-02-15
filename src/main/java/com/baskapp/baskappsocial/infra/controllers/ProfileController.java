package com.baskapp.baskappsocial.infra.controllers;

import com.baskapp.baskappsocial.data.dtos.request.CreateProfileDto;
import com.baskapp.baskappsocial.data.dtos.response.ResponseBodyDto;
import com.baskapp.baskappsocial.data.models.User;
import com.baskapp.baskappsocial.infra.notations.Authenticated;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping
    @Authenticated
    public ResponseEntity<ResponseBodyDto<String>> getProfile(HttpServletRequest request) {
        User user = (User) request.getAttribute("authenticatedUser");

        return ResponseEntity.ok().body(new ResponseBodyDto<>("success", 200, user.getId().toString()));
    }

    @PostMapping
    @Authenticated
    public ResponseEntity<ResponseBodyDto<String>> createProfile(HttpServletRequest request, @Valid @RequestBody CreateProfileDto profile) {
        User user = (User) request.getAttribute("authenticatedUser");
        return ResponseEntity.ok().body(new ResponseBodyDto<>("success", 200, user.getId().toString()));
    }
}
