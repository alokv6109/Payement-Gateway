package com.alokcodesback.paymentGateway.payloads;

import com.alokcodesback.paymentGateway.payloads.enums.InstrumentType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TransactionsDto {

    @NotEmpty
    private Double amount;

    @NotEmpty
    private Long senderId;

    @NotEmpty
    private Long receiverId;


    @NotEmpty
    private InstrumentType debitInstrumentType;


    @NotEmpty
    private InstrumentType creditInstrumentType;

    @NotEmpty
    private Long senderInstrumentId;

    @NotEmpty
    private Long receiverInstrumentId;

}
