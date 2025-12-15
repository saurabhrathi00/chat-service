package com.chat_service.chat_service;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class ChatServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ChatServiceApplication.class, args);

    }
}
