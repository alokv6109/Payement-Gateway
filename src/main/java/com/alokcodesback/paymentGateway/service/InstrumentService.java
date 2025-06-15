package com.alokcodesback.paymentGateway.service;

import com.alokcodesback.paymentGateway.entity.CardDetails;
import com.alokcodesback.paymentGateway.entity.InstrumentDetails;
import com.alokcodesback.paymentGateway.payloads.InstrumentDto;
import com.alokcodesback.paymentGateway.payloads.enums.InstrumentType;

public interface InstrumentService {
    InstrumentDetails addInstrument(InstrumentDto instrumentDto);


    InstrumentDetails getInstrumentDetailsForUser(Long instrumentId, Long userId, InstrumentType instrumentType);
}
