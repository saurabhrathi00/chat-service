package com.chat_service.chat_service.controllers;

import lombok.RequiredArgsConstructor;
import com.chat_service.chat_service.models.dao.ConversationEntity;
import com.chat_service.chat_service.models.request.CreateConversationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.chat_service.chat_service.services.ConversationService;

@RestController
@RequestMapping("v1/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @PostMapping
    @PreAuthorize("hasAuthority('chat.conversation.create')")
    public ResponseEntity<ConversationEntity> createConversation(@RequestBody CreateConversationRequest request) {

        ConversationEntity conversation = conversationService.createConversation(
                request.getParticipants(),
                request.getName(),
                request.getIsGroup()
        );
        return ResponseEntity.ok(conversation);
    }

    // Optional: List all conversations for a user
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<?> getUserConversations(@PathVariable String userId) {
//        return ResponseEntity.ok(conversationService.getUserConversations(userId));
//    }
}

