package com.chat_service.chat_service.models.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "conversations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversationEntity {
    @Id
    private String id; // ULID

    @Column(nullable = false)
    private String name; // optional for group

    @Column(nullable = false)
    private boolean isGroup;

    @ElementCollection
    @CollectionTable(name = "conversation_users", joinColumns = @JoinColumn(name = "conversation_id"))
    @Column(name = "user_id")
    private Set<String> participants; // ULID of users

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}

