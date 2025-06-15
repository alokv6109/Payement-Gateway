package com.alokcodesback.paymentGateway.payloads;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class CardExpiryDateDto {
    private boolean valid;
    private LocalDate localDate;
}
