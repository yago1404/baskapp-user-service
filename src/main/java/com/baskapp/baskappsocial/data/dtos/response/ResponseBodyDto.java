package com.baskapp.baskappsocial.data.dtos.response;

public record ResponseBodyDto<T>(String message, int statusCode, T data) {
}
