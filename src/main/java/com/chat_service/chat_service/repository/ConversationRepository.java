package com.chat_service.chat_service.repository;

import com.chat_service.chat_service.models.dao.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<ConversationEntity, String> {

    /**
     * Check if 1:1 conversation already exists between two users
     */
    @Query("""
    SELECT c FROM ConversationEntity c
    WHERE c.isGroup = false
      AND SIZE(c.participants) = 2
      AND :user1 MEMBER OF c.participants
      AND :user2 MEMBER OF c.participants
""")
    Optional<ConversationEntity> find1To1Conversation(
            @Param("user1") String user1,
            @Param("user2") String user2);

    /**
     * List all conversations for a user
     */
    @Query("SELECT c FROM ConversationEntity c JOIN c.participants p WHERE p = :userId")
    List<ConversationEntity> findByParticipant(@Param("userId") String userId);
}