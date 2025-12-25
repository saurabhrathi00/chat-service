package com.chat_service.chat_service.models.request;

import lombok.Data;

import java.util.List;

@Data
public class ServiceTokenRequest {
    private String clientId;
    private String clientSecret;
    private String audience;  // target service
    private List<String> scopes = null;
}

