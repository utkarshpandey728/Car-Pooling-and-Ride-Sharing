package com.carpooling.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carpooling.backend.Model.ChatRoom;

import java.util.Optional;
import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {
    Optional<ChatRoom> findByBooking_Id(UUID bookingId);
}
