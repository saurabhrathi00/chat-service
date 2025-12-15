package com.chat_service.chat_service.validators;


import com.chat_service.chat_service.exceptions.BadRequestException;

import java.util.Set;

public interface ConversationValidator {
    void validateCreateConversation(Set<String> participants, Boolean isGroup, String name) throws BadRequestException;
}
