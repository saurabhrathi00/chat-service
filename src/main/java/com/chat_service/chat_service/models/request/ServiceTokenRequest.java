package com.chat_service.chat_service.models.request;

import lombok.Data;

@Data
public class ServiceTokenRequest {
    private String clientId;
    private String clientSecret;
    private String audience;  // target service
}

