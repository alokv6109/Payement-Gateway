package com.alokcodesback.paymentGateway.payloads;

import com.alokcodesback.paymentGateway.payloads.enums.InstrumentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InstrumentDto {
    @NotNull
    private Long userId;

    @NotNull(message = "user name can not be blank")
    private String userName;

    @NotNull
    private InstrumentType instrumentType;

    private AccountDetailsDto accountDetails;

    private CardDto cardDetails;
}
