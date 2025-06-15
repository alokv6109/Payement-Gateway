package com.alokcodesback.paymentGateway.payloads;

import com.alokcodesback.paymentGateway.entity.InstrumentDetails;
import com.alokcodesback.paymentGateway.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InstrumentDetailsResponse {
    private InstrumentDetails instrumentDetails;
    private User user;
}

