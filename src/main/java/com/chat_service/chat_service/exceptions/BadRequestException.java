package com.chat_service.chat_service.exceptions;

public class BadRequestException extends AppException{
    public BadRequestException(String message) {
        super(message);
    }
}
