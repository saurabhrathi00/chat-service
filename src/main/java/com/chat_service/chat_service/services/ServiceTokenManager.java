package com.chat_service.chat_service.services;

import com.chat_service.chat_service.cache.ServiceTokenCache;
import com.chat_service.chat_service.client.AuthServiceClient;
import com.chat_service.chat_service.configurations.SecretsConfiguration;
import com.chat_service.chat_service.configurations.ServiceConfiguration;
import com.chat_service.chat_service.models.request.ServiceTokenRequest;
import com.chat_service.chat_service.models.response.ServiceTokenResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.Instant;


@Component
@RequiredArgsConstructor
public class ServiceTokenManager {

    private final ServiceTokenCache tokenCache;
    private final AuthServiceClient authServiceClient;
    private final SecretsConfiguration secretsConfiguration;
    private final ServiceConfiguration serviceConfiguration;

    @PostConstruct
    public void init() {
        // Fetch tokens at startup
        for (String audience : serviceConfiguration.getTargetServices()) {
            fetchAndCacheToken(audience);
        }
    }

    /**
     * Get token for a given audience.
     * Refresh only if missing or about to expire.
     */
    public String getToken(String audience) {
        String token = tokenCache.getToken(audience);
        if (null == token) {
            token = fetchAndCacheToken(audience);
        }
        return token;
    }

    private String fetchAndCacheToken(String audience) {

        try {
            ServiceTokenRequest request = new ServiceTokenRequest();
            request.setClientId(secretsConfiguration.getChatService().getId());
            request.setClientSecret(secretsConfiguration.getChatService().getPassword());
            request.setAudience(audience);
            request.setScopes(serviceConfiguration.getUserService().getScopes());
            ServiceTokenResponse response = authServiceClient.requestToken(request);
            Instant expiry = Instant.now().plusSeconds(response.getExpiresIn() - 60);
            tokenCache.storeToken(audience, response.getToken(), expiry);
            return response.getToken();
        } catch (Exception e) {
            return null;
        }

    }
}
