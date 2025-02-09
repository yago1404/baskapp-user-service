package com.baskapp.baskappsocial.data.dtos.exceptions;

import lombok.Getter;
import lombok.Setter;

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