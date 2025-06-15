package com.alokcodesback.paymentGateway.service.impl;

import com.alokcodesback.paymentGateway.entity.Transactions;
import com.alokcodesback.paymentGateway.payloads.TransactionsDto;
import com.alokcodesback.paymentGateway.service.TransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionsServiceImpl implements TransactionsService {


    @Override
    public Transactions createTransactions(TransactionsDto transactionsDto) {
        return null;
    }
}
