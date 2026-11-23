package com.carpooling.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ChatMessageResponseDTO {
    private UUID messageId;
    private UUID senderId;
    private String message;
    private LocalDateTime sentAt;
}
