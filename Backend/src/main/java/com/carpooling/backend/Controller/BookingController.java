package com.carpooling.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.carpooling.backend.DTO.BookingResponse;
import com.carpooling.backend.DTO.UpdateBookingStatusRequest;
import com.carpooling.backend.Model.Booking;
import com.carpooling.backend.Model.User;
import com.carpooling.backend.Service.BookingService;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {
        "http://127.0.0.1:5500/", "http://localhost:5173"
})
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/rides/{rideId}/bookings")
    public ResponseEntity<BookingResponse> requestRide(
            @PathVariable UUID rideId,
            @AuthenticationPrincipal User passenger) {
        Booking newBooking = bookingService.createBooking(rideId, passenger);
        return new ResponseEntity<>(new BookingResponse(newBooking), HttpStatus.CREATED);
    }

    @PatchMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingResponse> respondToBooking(
            @PathVariable UUID bookingId,
            @Valid @RequestBody UpdateBookingStatusRequest statusRequest,
            @AuthenticationPrincipal User driver) {
        Booking updatedBooking = bookingService.updateBookingStatus(bookingId, statusRequest, driver);
        return ResponseEntity.ok(new BookingResponse(updatedBooking));
    }

    @GetMapping("/bookings/my-bookings")
    public ResponseEntity<List<BookingResponse>> getMyBookings(
            @AuthenticationPrincipal User user) {
        List<Booking> passengerBookings = bookingService.getPassengerBookings(user);
        List<Booking> driverBookings = bookingService.getDriverBookings(user);


        Set<Booking> allBookings = new LinkedHashSet<>();
        allBookings.addAll(passengerBookings);
        allBookings.addAll(driverBookings);

        List<BookingResponse> response = allBookings.stream()
                .map(BookingResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}