package com.alokcodesback.paymentGateway.payloads;

import com.alokcodesback.paymentGateway.entity.AccountDetails;
import com.alokcodesback.paymentGateway.entity.CardDetails;
import lombok.Data;

import java.util.List;

@Data
public class InstrumentAgreegatorDto {

    private Long userId;

    List<AccountDetails> accountDetailsList;

    List<CardDetails> cardDetails;
}
