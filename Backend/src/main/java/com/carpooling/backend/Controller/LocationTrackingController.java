package com.carpooling.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.carpooling.backend.DTO.LocationResponseDto;
import com.carpooling.backend.DTO.LocationUpdateDto;
import com.carpooling.backend.Model.User;
import com.carpooling.backend.Service.LocationTrackingService;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/location")
@CrossOrigin(origins =
        {
                "http://127.0.0.1:5500/","http://localhost:5173/"
        })

public class LocationTrackingController {

    private final LocationTrackingService locationService;

    public LocationTrackingController(LocationTrackingService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/update")
    public ResponseEntity<LocationResponseDto> updateLocation(
            @Valid @RequestBody LocationUpdateDto dto,
            @AuthenticationPrincipal User user) {
        LocationResponseDto response = locationService.updateLocation(dto, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/driver/{rideId}")
    public ResponseEntity<LocationResponseDto> getDriverLocation(
            @PathVariable UUID rideId,
            @AuthenticationPrincipal User user) {
        LocationResponseDto response = locationService.getDriverLocation(rideId, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/passenger/{rideId}")
    public ResponseEntity<LocationResponseDto> getPassengerLocation(
            @PathVariable UUID rideId,
            @AuthenticationPrincipal User driver) {
        LocationResponseDto response = locationService.getPassengerLocation(rideId, driver);
        return ResponseEntity.ok(response);
    }

    @MessageMapping("/location.update")
    public void handleLocationUpdate(@Payload LocationUpdateDto dto, Principal principal) {
    }
}
