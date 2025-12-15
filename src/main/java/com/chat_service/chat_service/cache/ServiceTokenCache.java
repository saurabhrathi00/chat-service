package com.chat_service.chat_service.cache;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@Component
public class ServiceTokenCache {

    @Data
    private static class CachedToken {
        private String token;
        private Instant expiry;
    }

    private final Map<String, CachedToken> cache = new ConcurrentHashMap<>();

    public String getToken(String audience) {
        CachedToken cachedToken = cache.get(audience);
        if (cachedToken == null || Instant.now().isAfter(cachedToken.getExpiry())) {
            return null;
        }
        return cachedToken.token;
    }

    public void storeToken(String audience, String token, Instant expiry) {
        CachedToken ct = new CachedToken();
        ct.setToken(token);
        ct.setExpiry(expiry);
        cache.put(audience, ct);
    }
}

