package com.chat_service.chat_service.exceptions;

public class ConversationCreationException extends RuntimeException {
    public ConversationCreationException(String message) {
        super(message);
    }
}
