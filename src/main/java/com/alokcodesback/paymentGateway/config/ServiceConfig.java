package com.alokcodesback.paymentGateway.config;

import com.alokcodesback.paymentGateway.payloads.enums.InstrumentType;
import com.alokcodesback.paymentGateway.service.InstrumentService;
import com.alokcodesback.paymentGateway.service.impl.AccountDetailsService;
import com.alokcodesback.paymentGateway.service.impl.CardDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServiceConfig {
    @Bean
    public Map<InstrumentType, InstrumentService> serviceMap(AccountDetailsService bankService, CardDetailsService cardService) {
        return Map.of(
                InstrumentType.ACCOUNT, bankService,
                InstrumentType.CARD, cardService
        );
    }
}

