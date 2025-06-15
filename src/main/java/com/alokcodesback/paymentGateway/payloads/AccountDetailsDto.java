package com.alokcodesback.paymentGateway.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDetailsDto {
    @NotNull
    @NotBlank
    private String accountNumber; // Bank details

    @NotNull
    @NotBlank
    private String bankName;

    @NotNull
    @NotBlank
    private String ifscCode;
}
