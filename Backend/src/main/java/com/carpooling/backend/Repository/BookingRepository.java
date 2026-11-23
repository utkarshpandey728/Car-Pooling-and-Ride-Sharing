package com.carpooling.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carpooling.backend.Model.Booking;
import com.carpooling.backend.Model.BookingStatus;
import com.carpooling.backend.Model.Ride;
import com.carpooling.backend.Model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    Optional<Booking> findByRideIdAndPassengerId(UUID rideId, UUID passengerId);

    List<Booking> findByRideAndStatus(Ride ride, BookingStatus status);

    Optional<Booking> findByRideAndPassenger(Ride ride, User passenger);

    List<Booking> findByPassengerOrderByCreatedAtDesc(User passenger);

    List<Booking> findByRide(Ride ride);
}