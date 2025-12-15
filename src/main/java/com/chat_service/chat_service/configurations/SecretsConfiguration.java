package com.chat_service.chat_service.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "secrets")
public class SecretsConfiguration {

    private Jwt jwt;
    private Datasource datasource;
    private ChatService chatService;

    @Data
    public static class Jwt {
        private String secret;
    }

    @Data
    public static class Datasource {
        private String username;
        private String password;
        private String driverClassName; // matches driver-class-name
    }

    @Data
    public static class ChatService {
        private String id;
        private String password;
    }
}
