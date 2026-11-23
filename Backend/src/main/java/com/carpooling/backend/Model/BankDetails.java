package com.carpooling.backend.Model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDetails {
    private String accountNumber;
    private String ifscCode;
    private String accountHolderName;
    private String bankName;
}
