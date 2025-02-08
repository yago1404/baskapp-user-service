package com.baskapp.baskappsocial.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LoggedDto {
    private String token;
    private String refreshToken;
}
