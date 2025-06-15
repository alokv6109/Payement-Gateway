package com.alokcodesback.paymentGateway.service;

import com.alokcodesback.paymentGateway.entity.Transactions;
import com.alokcodesback.paymentGateway.payloads.TransactionsDto;
import com.alokcodesback.paymentGateway.payloads.ValidTransactionResponseDto;
import com.alokcodesback.paymentGateway.payloads.enums.TransactionStatus;

public interface TransactionsService {
    Transactions createTransactions(TransactionsDto transactionsDto, ValidTransactionResponseDto validateTransactionsRelatedInfo);

    boolean updateTransactionResponse(String transactionId, TransactionStatus transactionStatus);
}
