package com.carpooling.backend.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ChatMessageRequestDTO {
    private UUID bookingId;
    private String message;
}
