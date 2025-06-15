package com.alokcodesback.paymentGateway.config;

import com.alokcodesback.paymentGateway.payloads.enums.InstrumentType;
import com.alokcodesback.paymentGateway.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InstrumentServiceFactory {


    private final Map<InstrumentType, InstrumentService> serviceMap;

    @Autowired
    public InstrumentServiceFactory(Map<InstrumentType, InstrumentService> serviceMap) {
        this.serviceMap = serviceMap;
    }


    public InstrumentService getService(InstrumentType instrumentType) {
        return serviceMap.get(instrumentType);
    }


}
