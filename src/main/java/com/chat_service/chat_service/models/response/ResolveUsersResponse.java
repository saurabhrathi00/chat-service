package com.chat_service.chat_service.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResolveUsersResponse {
    private List<UserMapping> users;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class UserMapping {
        private String username;
        private String userId;
    }
}