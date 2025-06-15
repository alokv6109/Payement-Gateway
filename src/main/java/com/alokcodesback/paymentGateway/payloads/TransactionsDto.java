package com.alokcodesback.paymentGateway.payloads;

import com.alokcodesback.paymentGateway.payloads.enums.InstrumentType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionsDto {

    @NotNull
    private Double amount;

    @NotNull
    private Long senderId;

    @NotNull
    private Long receiverId;


    @NotNull
    private InstrumentType debitInstrumentType;


    @NotNull
    private InstrumentType creditInstrumentType;

    @NotNull
    private Long senderInstrumentId;

    @NotNull
    private Long receiverInstrumentId;

}
