package com.alokcodesback.paymentGateway.service.impl;

import com.alokcodesback.paymentGateway.entity.AccountDetails;
import com.alokcodesback.paymentGateway.entity.CardDetails;
import com.alokcodesback.paymentGateway.entity.InstrumentDetails;
import com.alokcodesback.paymentGateway.entity.User;
import com.alokcodesback.paymentGateway.exception.ResourceNotFoundExcetion;
import com.alokcodesback.paymentGateway.payloads.AccountDetailsDto;
import com.alokcodesback.paymentGateway.payloads.InstrumentDto;
import com.alokcodesback.paymentGateway.payloads.enums.InstrumentType;
import com.alokcodesback.paymentGateway.repository.AccountDetailsRepository;
import com.alokcodesback.paymentGateway.service.InstrumentService;
import com.alokcodesback.paymentGateway.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
public class AccountDetailsService implements InstrumentService {

    private final AccountDetailsRepository accountDetailsRepository;

    private final UserService userService;

    @Autowired
    public AccountDetailsService(AccountDetailsRepository accountDetailsRepository, UserService userService) {
        this.accountDetailsRepository = accountDetailsRepository;
        this.userService = userService;
    }

    @Override
    public AccountDetails addInstrument(InstrumentDto instrumentDto) {
        log.info("the addInstrument() method is called with InstrumentDto obj : {}" , instrumentDto);
        if(!instrumentDto.getInstrumentType().toString().equals(InstrumentType.ACCOUNT.toString())){
            throw new NullPointerException("The instrument Type is not valid for this type Request type");
        }
        log.info("Validating the userId : {} if it exists in Database.", instrumentDto.getUserId());
        User user = userService.getUser(instrumentDto.getUserId());
        user.setUpdatedAt(LocalDateTime.now());

        AccountDetailsDto accountDetailsDto = instrumentDto.getAccountDetails();
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountNumber(accountDetailsDto.getAccountNumber());
        accountDetails.setBankName(accountDetailsDto.getBankName());
        accountDetails.setIfscCode(accountDetailsDto.getIfscCode());
        accountDetails.setUserName(instrumentDto.getUserName());
        accountDetails.setUser(user);

        return this.accountDetailsRepository.save(accountDetails);

    }


    @Override
    public InstrumentDetails getInstrumentDetailsForUser(Long instrumentId, Long userId, InstrumentType instrumentType) {
        if(!instrumentType.toString().equals(InstrumentType.ACCOUNT.toString())){
            throw new NullPointerException("The instrument Type is not valid for this type Request type");
        }

        log.info("Validating the userId : {} if it exists in Database.", userId);
        userService.validateUserIdExists(userId);

        AccountDetails accountDetails = this.accountDetailsRepository.findByAccountDetailsIdAndUserId(instrumentId, userId).orElseThrow(
                () -> {throw new ResourceNotFoundExcetion("accountDetails", "instrument id and userId", instrumentId);});

        log.info("the account details entity returned is : {}", accountDetails.getAccountNumber());

        return accountDetails;
    }
}
