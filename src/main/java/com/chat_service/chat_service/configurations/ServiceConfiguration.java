package com.chat_service.chat_service.configurations;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "configs")
@Data
public class ServiceConfiguration {

    private ChatDb chatDb;
    private AuthService authService;
    private UserService userService;
    private List<String> targetServices;
    @Data
    public static class ChatDb {
        private String name;
        private String url;
    }

    @Data
    public static class AuthService {
        private String url;
    }

    @Data
    public static class UserService {
        private String url;
    }
}
