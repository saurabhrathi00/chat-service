package com.chat_service.chat_service.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ServiceTokenResponse {
    private String token;
    private long expiresIn; // seconds
}

