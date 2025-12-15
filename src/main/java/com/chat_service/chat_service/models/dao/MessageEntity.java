package com.chat_service.chat_service.models.dao;

import jakarta.persistence.*;
import lombok.*;
import com.chat_service.chat_service.models.enums.MessageStatus;
import com.chat_service.chat_service.models.enums.MessageType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageEntity {
    @Id
    private String id; // ULID

    @Column(nullable = false)
    private String conversationId; // ULID of conversation

    @Column(nullable = false)
    private String senderId; // ULID

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageType messageType; // TEXT, IMAGE, VIDEO, etc.

    @Column(columnDefinition = "TEXT")
    private String content; // text or media URL/JSON

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageStatus status; // PENDING, SENT, DELIVERED, READ

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}

