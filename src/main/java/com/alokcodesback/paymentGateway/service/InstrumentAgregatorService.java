package com.alokcodesback.paymentGateway.service;

import com.alokcodesback.paymentGateway.payloads.InstrumentAgreegatorDto;

public interface InstrumentAgregatorService {

    InstrumentAgreegatorDto getAllInstruments(long userId);
}
