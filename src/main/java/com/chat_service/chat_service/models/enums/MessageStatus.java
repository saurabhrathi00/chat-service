package com.chat_service.chat_service.models.enums;

public enum MessageStatus {
    PENDING,    // Message created, not yet sent
    SENT,       // Message sent from sender's client
    DELIVERED,  // Recipient client received message
    READ        // Recipient read the message
}
