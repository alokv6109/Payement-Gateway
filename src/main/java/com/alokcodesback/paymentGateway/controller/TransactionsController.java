package com.alokcodesback.paymentGateway.controller;


import com.alokcodesback.paymentGateway.config.InstrumentServiceFactory;
import com.alokcodesback.paymentGateway.entity.InstrumentDetails;
import com.alokcodesback.paymentGateway.entity.Transactions;
import com.alokcodesback.paymentGateway.exception.InstrumentTypeException;
import com.alokcodesback.paymentGateway.payloads.ApiResponse;
import com.alokcodesback.paymentGateway.payloads.InstrumentDetailsResponse;
import com.alokcodesback.paymentGateway.payloads.TransactionsDto;
import com.alokcodesback.paymentGateway.payloads.ValidTransactionResponseDto;
import com.alokcodesback.paymentGateway.payloads.enums.TransactionStatus;
import com.alokcodesback.paymentGateway.service.InstrumentService;
import com.alokcodesback.paymentGateway.service.TransactionsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        ValidTransactionResponseDto validateTransactionsRelatedInfo = validateTransactionsRelatedInfo(transactionsDto);
        if(!validateTransactionsRelatedInfo.isValid()){
            throw new IllegalArgumentException("The values provided in transactionDto is not right, Please check again!!");
        }

        Transactions transactions = this.transactionsService.createTransactions(transactionsDto, validateTransactionsRelatedInfo);
        return ResponseEntity.ok(transactions);
    }



    @PatchMapping("/callback/{transactionId}")
    public ResponseEntity<ApiResponse> callbackForPayemntStatusFromProcessor(@PathVariable String transactionId,
        @RequestParam String paymentResponse){
        TransactionStatus transactionStatus = TransactionStatus.valueOf(paymentResponse);
        boolean updateTransactionResponse = this.transactionsService.updateTransactionResponse(transactionId, transactionStatus);
        if(updateTransactionResponse){
            return ResponseEntity.ok(new ApiResponse("The transaction has been updated now with " +
                    "trasnaction Id as " + transactionId, true));
        }

        return new ResponseEntity<>(new ApiResponse("the transaction could " +
                "not be updated succesfully due to some error", false), HttpStatus.BAD_REQUEST);
    }


    private ValidTransactionResponseDto validateTransactionsRelatedInfo(TransactionsDto transactionsDto) {
        InstrumentService debitService = instrumentServiceFactory.getService(transactionsDto.getDebitInstrumentType());
        InstrumentService creditService=  instrumentServiceFactory.getService(transactionsDto.getCreditInstrumentType());
        if (debitService == null || creditService== null) {
            log.info("the instrument type did not got verified debit instrument Type: {} and credit instrument type : {}",
                    transactionsDto.getDebitInstrumentType(), transactionsDto.getCreditInstrumentType());
            throw new InstrumentTypeException("The instrument type is not correct , found credit type = " + transactionsDto.getCreditInstrumentType()
                    + " or debit instrument type = " + transactionsDto.getDebitInstrumentType());
        }
        InstrumentDetailsResponse instrumentDetailsForDebit = debitService.getInstrumentDetailsForUser(transactionsDto.getSenderInstrumentId(),
                transactionsDto.getSenderId(), transactionsDto.getDebitInstrumentType());
        InstrumentDetailsResponse instrumentDetailsForCredit = creditService.getInstrumentDetailsForUser(transactionsDto.getReceiverInstrumentId(),
                transactionsDto.getReceiverId(), transactionsDto.getCreditInstrumentType());
        if(instrumentDetailsForCredit != null && instrumentDetailsForDebit!= null){
            return ValidTransactionResponseDto.builder().valid(true).senderUser(instrumentDetailsForDebit.getUser())
                    .receiverUser(instrumentDetailsForCredit.getUser()).build();
        }
        return ValidTransactionResponseDto.builder().valid(false).senderUser(null).receiverUser(null).build();

    }
}
