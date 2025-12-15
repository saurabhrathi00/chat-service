package com.chat_service.chat_service.client;

import com.chat_service.chat_service.configurations.ServiceConfiguration;
import com.chat_service.chat_service.models.request.ServiceTokenRequest;
import com.chat_service.chat_service.models.response.ServiceTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthServiceClient {

    private final RestTemplate restTemplate;
    private final ServiceConfiguration serviceConfiguration;

    public ServiceTokenResponse requestToken(ServiceTokenRequest request) {
        return restTemplate.postForObject(serviceConfiguration.getAuthService().getUrl() + "internal/token"
                , request, ServiceTokenResponse.class);
    }
}

