package com.alokcodesback.paymentGateway.service;

import com.alokcodesback.paymentGateway.entity.Transactions;
import com.alokcodesback.paymentGateway.payloads.TransactionsDto;

public interface TransactionsService {
    Transactions createTransactions(TransactionsDto transactionsDto);
}
