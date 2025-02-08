package com.baskapp.baskappsocial.dtos.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ErrorResponse {
    private int status;
    private String message;
    private String error;

    public ErrorResponse(int status, String message, String error) {
        this.status = status;
        this.message = message;
        this.error = error;
    }
}