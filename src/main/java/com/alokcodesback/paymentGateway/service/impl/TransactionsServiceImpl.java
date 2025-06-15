package com.alokcodesback.paymentGateway.service.impl;

import com.alokcodesback.paymentGateway.entity.Transactions;
import com.alokcodesback.paymentGateway.exception.ResourceNotFoundExcetion;
import com.alokcodesback.paymentGateway.payloads.TransactionsDto;
import com.alokcodesback.paymentGateway.payloads.ValidTransactionResponseDto;
import com.alokcodesback.paymentGateway.payloads.enums.TransactionStatus;
import com.alokcodesback.paymentGateway.repository.TransactionsRepostory;
import com.alokcodesback.paymentGateway.service.TransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Slf4j
@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private TransactionsRepostory transactionsRepostory;



    @Override
    public Transactions createTransactions(TransactionsDto transactionsDto, ValidTransactionResponseDto validateTransactionsRelatedInfo) {
        log.info("the valid transactions object passed to service :{}", transactionsDto);

        //simulating the call for checking the balanace of the debitter user
        boolean ifDebitterHasEnoughBalance = checkIfDebitterHasEnoughBalance(transactionsDto.getAmount(),
                transactionsDto.getSenderId());
        if(!ifDebitterHasEnoughBalance){
            throw new IllegalArgumentException("Not enough balance with the user");
        }
        Transactions transactions = new Transactions();
        transactions.setTransactionStatus(TransactionStatus.PENDING);
        transactions.setAmount(transactionsDto.getAmount());
        transactions.setCreditInstrumentType(transactionsDto.getCreditInstrumentType());
        transactions.setDebitInstrumentType(transactionsDto.getDebitInstrumentType());
        transactions.setSenderInstrumentId(transactionsDto.getSenderInstrumentId());
        transactions.setReceiverInstrumentId(transactionsDto.getReceiverInstrumentId());
        transactions.setReceiver(validateTransactionsRelatedInfo.getReceiverUser());
        transactions.setSender(validateTransactionsRelatedInfo.getSenderUser());

        return this.transactionsRepostory.save(transactions);
    }

    @Override
    public boolean updateTransactionResponse(String transactionId, TransactionStatus transactionStatus) {
        Transactions transactions = this.transactionsRepostory.findById(transactionId).orElseThrow(() ->
        {
            throw new ResourceNotFoundExcetion("Transactions", "id", 0);
        });
        transactions.setUpdatedAt(LocalDateTime.now());
        transactions.setTransactionStatus(transactionStatus);
        this.transactionsRepostory.save(transactions);
        return true;
    }


    private boolean checkIfDebitterHasEnoughBalance(Double amount, Long senderId) {
        log.info("The sender with id : {} wantes to send amout : {}", senderId, amount);
        log.info("Checking the balance for the user with userId : {}", senderId);
        log.info("Enough balnce to serve the request for amout : {} bu userId : {}", amount, senderId);
        return true;
    }
}
