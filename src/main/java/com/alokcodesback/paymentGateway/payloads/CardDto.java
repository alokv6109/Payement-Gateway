package com.alokcodesback.paymentGateway.payloads;

import com.alokcodesback.paymentGateway.payloads.enums.CardType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
public class CardDto {

    @NotEmpty
    @Size(min = 16, max = 16)
    private String cardNumber;    // Card details

    @NotEmpty
    private CardType cardType;

    @NotEmpty
    private String expiryDate;

    @NotEmpty
    private Integer cvv;
}
