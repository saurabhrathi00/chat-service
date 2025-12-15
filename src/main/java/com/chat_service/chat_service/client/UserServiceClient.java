package com.chat_service.chat_service.client;

import com.chat_service.chat_service.configurations.ServiceConfiguration;
import com.chat_service.chat_service.models.request.ResolveUsersRequest;
import com.chat_service.chat_service.models.response.ResolveUsersResponse;
import com.chat_service.chat_service.services.ServiceTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UserServiceClient {

    private final RestTemplate restTemplate;
    private final ServiceConfiguration serviceConfiguration;
    private final ServiceTokenManager serviceTokenManager;

    public ResolveUsersResponse resolveUserId(ResolveUsersRequest request) {

        String url = serviceConfiguration.getUserService().getUrl()
                + "/internal/user/resolve";

        // Include headers if needed, e.g., Authorization
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(serviceTokenManager.getToken("user-service"));
        HttpEntity<ResolveUsersRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<ResolveUsersResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                ResolveUsersResponse.class
        );
        return response.getBody();
    }
}
