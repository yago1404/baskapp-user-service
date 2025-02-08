package com.baskapp.baskappsocial.dtos.response;

public record ResponseBodyDto<T>(String message, int statusCode, T data) {
}
