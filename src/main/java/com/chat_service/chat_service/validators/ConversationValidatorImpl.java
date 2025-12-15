package com.chat_service.chat_service.validators;

import com.chat_service.chat_service.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ConversationValidatorImpl implements ConversationValidator {

    @Override
    public void validateCreateConversation(Set<String> participants, Boolean isGroup, String name) throws BadRequestException {
        if (isGroup == null) {
            throw new BadRequestException("isGroup is required");
        }

        if (!isGroup) { // 1:1 case
            if (participants == null || participants.size() != 1) {
                throw new BadRequestException("1:1 conversation must have exactly 2 participants");
            }
        } else { // group case
            if (name == null || name.isBlank()) {
                throw new BadRequestException("Group name is mandatory");
            }
            if (participants == null || participants.size() < 2) {
                throw new BadRequestException("Group must have at least 2 participants");
            }
        }
    }

}