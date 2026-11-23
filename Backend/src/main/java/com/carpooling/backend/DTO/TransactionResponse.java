package com.carpooling.backend.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import com.carpooling.backend.Model.Transaction;

@Data
public class TransactionResponse {
    private UUID id;
    private UUID rideId;
    private String riderName;
    private String driverName;
    private Double amount;
    private String type;
    private String status;
    private String paymentMethod;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    public TransactionResponse(Transaction transaction) {
        this.id = transaction.getId();
        this.rideId = transaction.getRide().getId();
        this.riderName = transaction.getRider().getFirstName() + " " + transaction.getRider().getLastName();
        this.driverName = transaction.getDriver().getFirstName() + " " + transaction.getDriver().getLastName();
        this.amount = transaction.getAmount();
        this.type = transaction.getType().name();
        this.status = transaction.getStatus().name();
        this.paymentMethod = transaction.getPaymentMethod();
        this.createdAt = transaction.getCreatedAt();
        this.completedAt = transaction.getCompletedAt();
    }
}
