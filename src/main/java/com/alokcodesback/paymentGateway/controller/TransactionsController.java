package com.alokcodesback.paymentGateway.controller;


import com.alokcodesback.paymentGateway.config.InstrumentServiceFactory;
import com.alokcodesback.paymentGateway.entity.InstrumentDetails;
import com.alokcodesback.paymentGateway.entity.Transactions;
import com.alokcodesback.paymentGateway.exception.InstrumentTypeException;
import com.alokcodesback.paymentGateway.payloads.TransactionsDto;
import com.alokcodesback.paymentGateway.service.InstrumentService;
import com.alokcodesback.paymentGateway.service.TransactionsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/payment")
public class TransactionsController {


    private final InstrumentServiceFactory instrumentServiceFactory;

    private final TransactionsService transactionsService;

    @Autowired
    public TransactionsController(InstrumentServiceFactory instrumentServiceFactory, TransactionsService transactionsService) {
        this.instrumentServiceFactory = instrumentServiceFactory;
        this.transactionsService = transactionsService;
    }


    @PostMapping("/create")
    public ResponseEntity<Transactions> createPayment(@Valid @RequestBody TransactionsDto transactionsDto){
        boolean validateTransactionsRelatedInfo = validateTransactionsRelatedInfo(transactionsDto);
        if(!validateTransactionsRelatedInfo){
            throw new IllegalArgumentException("The values provided in transactionDto is not right, Please check again!!");
        }

        Transactions transactions = this.transactionsService.createTransactions(transactionsDto);
        return ResponseEntity.ok(transactions);


    }

    private boolean validateTransactionsRelatedInfo(TransactionsDto transactionsDto) {
        InstrumentService debitService = instrumentServiceFactory.getService(transactionsDto.getDebitInstrumentType());
        InstrumentService creditService=  instrumentServiceFactory.getService(transactionsDto.getCreditInstrumentType());
        if (debitService == null || creditService== null) {
            log.info("the instrument type did not got verified debit instrument Type: {} and credit instrument type : {}",
                    transactionsDto.getDebitInstrumentType(), transactionsDto.getCreditInstrumentType());
            throw new InstrumentTypeException("The instrument type is not correct , found credit type = " + transactionsDto.getCreditInstrumentType()
                    + " or debit instrument type = " + transactionsDto.getDebitInstrumentType());
        }
        InstrumentDetails instrumentDetailsForDebit = debitService.getInstrumentDetailsForUser(transactionsDto.getSenderInstrumentId(),
                transactionsDto.getSenderId(), transactionsDto.getDebitInstrumentType());
        InstrumentDetails instrumentDetailsForUser = creditService.getInstrumentDetailsForUser(transactionsDto.getReceiverInstrumentId(),
                transactionsDto.getReceiverId(), transactionsDto.getCreditInstrumentType());
        if(instrumentDetailsForUser != null && instrumentDetailsForDebit!= null){
            return true;
        }
        return false;

    }
}
