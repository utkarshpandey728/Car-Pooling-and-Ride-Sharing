package com.carpooling.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carpooling.backend.Model.ChatMessage;

import java.util.List;
import java.util.UUID;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {
    List<ChatMessage> findByChatRoom_IdOrderBySentAtAsc(UUID chatRoomId);
}
