package com.baskapp.baskappsocial.controllers;

import com.baskapp.baskappsocial.dtos.request.CreateUserDto;
import com.baskapp.baskappsocial.dtos.request.LoginDto;
import com.baskapp.baskappsocial.dtos.response.LoggedDto;
import com.baskapp.baskappsocial.dtos.response.ResponseBodyDto;
import com.baskapp.baskappsocial.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseBodyDto<LoggedDto>> createUser(@Valid @RequestBody CreateUserDto user) {
        LoggedDto tokens = userService.createUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseBodyDto<>("success", 201, tokens));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBodyDto<LoggedDto>> login(@Valid @RequestBody LoginDto login) {
        System.out.println("Login");
        LoggedDto tokens = this.userService.doLogin(login);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseBodyDto<>("success", 200, tokens));
    }
}
