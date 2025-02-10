package com.baskapp.baskappsocial.infra.controllers;

import com.baskapp.baskappsocial.data.dtos.response.ResponseBodyDto;
import com.baskapp.baskappsocial.data.models.User;
import com.baskapp.baskappsocial.infra.notations.Authenticated;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping
    @Authenticated
    public ResponseEntity<ResponseBodyDto<String>> getProfile(HttpServletRequest request) {
        User user = (User) request.getAttribute("authenticatedUser");

        return ResponseEntity.ok().body(new ResponseBodyDto<>("success", 200, user.getId().toString()));
    }
}
