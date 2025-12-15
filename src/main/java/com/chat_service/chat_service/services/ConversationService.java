package com.chat_service.chat_service.services;


import com.chat_service.chat_service.client.UserServiceClient;
import com.chat_service.chat_service.exceptions.ConversationAlreadyExistsException;
import com.chat_service.chat_service.exceptions.ConversationCreationException;
import com.chat_service.chat_service.exceptions.ResourceNotFoundException;
import com.chat_service.chat_service.models.request.ResolveUsersRequest;
import com.chat_service.chat_service.models.response.ResolveUsersResponse;
import de.huxhorn.sulky.ulid.ULID;
import lombok.RequiredArgsConstructor;
import com.chat_service.chat_service.models.dao.ConversationEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.chat_service.chat_service.repository.ConversationRepository;
import com.chat_service.chat_service.validators.ConversationValidator;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final ConversationValidator conversationValidator;
    private final UserServiceClient userServiceClient;

    /**
     * Create 1:1 conversation between two users.
     * If conversation already exists, return existing one.
     */
    public ConversationEntity createConversation(Set<String> participants, String name, Boolean isGroup) {
        try {
            // ✅ Step 0: always include current user
            String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

            // ✅ Step 1: validate first
            conversationValidator.validateCreateConversation(participants, isGroup, name);

            // ✅ Step 2: if 1:1 → check if already exists
            if (!isGroup) {
                ResolveUsersRequest resolveUsersRequest = ResolveUsersRequest.builder()
                        .usernames(new ArrayList<>(participants))
                        .build();
                ResolveUsersResponse resolveUsersResponse = userServiceClient.resolveUserId(resolveUsersRequest);
                String otherUser = resolveUsersResponse.getUsers().stream()
                        .findFirst()
                        .map(ResolveUsersResponse.UserMapping::getUserId)
                        .orElseThrow(() -> new ResourceNotFoundException("No user mappings returned"));
                Optional<ConversationEntity> existing = conversationRepository.find1To1Conversation(currentUser, otherUser);
                if (existing.isPresent()) {
                    throw new ConversationAlreadyExistsException(
                            "1:1 conversation already exists between " + currentUser + " and " + otherUser
                    );
                }
            }

            String conversationName =
                    (name != null && !name.isEmpty()) ? name : generateDefault1To1Name(participants);

            ConversationEntity conversation = ConversationEntity.builder()
                    .id(new ULID().nextULID())
                    .participants(participants)
                    .isGroup(isGroup)
                    .name(conversationName)
                    .build();

            return conversationRepository.save(conversation);

        } catch (IllegalArgumentException | IllegalStateException e) {
            throw new ConversationCreationException("Failed to create conversation: " + e.getMessage());
        }
    }


    /**
     * List all conversations for a user
     */
    public Object getUserConversations(String userId) {
        return conversationRepository.findByParticipant(userId);
    }

    /**
     * Optional: generate default name for 1:1 conversation
     */
    private String generateDefault1To1Name(Set<String> participants) {
        List<String> sortedParticipants = new ArrayList<>(participants);
        Collections.sort(sortedParticipants);
        return String.join(", ", sortedParticipants);
    }
}

