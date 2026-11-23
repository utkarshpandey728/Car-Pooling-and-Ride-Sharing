package com.carpooling.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carpooling.backend.Model.Notification;
import com.carpooling.backend.Model.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    List<Notification> findByUserOrderByCreatedAtDesc(User user);

    List<Notification> findByUserAndIsReadFalseOrderByCreatedAtDesc(User user);

    List<Notification> findByUserAndIsReadFalse(User user);

    Long countByUserAndIsReadFalse(User user);
}