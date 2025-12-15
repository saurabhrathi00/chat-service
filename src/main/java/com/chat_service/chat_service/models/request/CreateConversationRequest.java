package com.chat_service.chat_service.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateConversationRequest {
    private Set<String> participants; // ULID of users
    private String name;              // optional, for 1:1 can be null, for group mandatory in future
    private Boolean isGroup;          // "true" or "false"
}