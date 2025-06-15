package com.alokcodesback.paymentGateway.service.impl;

import com.alokcodesback.paymentGateway.entity.CardDetails;
import com.alokcodesback.paymentGateway.entity.User;
import com.alokcodesback.paymentGateway.exception.DateTimeRelatedException;
import com.alokcodesback.paymentGateway.exception.ResourceNotFoundExcetion;
import com.alokcodesback.paymentGateway.payloads.CardDto;
import com.alokcodesback.paymentGateway.payloads.CardExpiryDateDto;
import com.alokcodesback.paymentGateway.payloads.InstrumentDetailsResponse;
import com.alokcodesback.paymentGateway.payloads.InstrumentDto;
import com.alokcodesback.paymentGateway.payloads.enums.InstrumentType;
import com.alokcodesback.paymentGateway.repository.CardDetailsRepository;
import com.alokcodesback.paymentGateway.service.InstrumentService;
import com.alokcodesback.paymentGateway.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
@Service
public class CardDetailsService implements InstrumentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    @Autowired
    private UserService userService;
    @Override
    public CardDetails addInstrument(InstrumentDto instrumentDto) {
        log.info("the addInstrument() method is called with InstrumentDto obj : {}" , instrumentDto);
        log.info("Validating the userId : {} if it exists in Database.", instrumentDto.getUserId());
        if(!instrumentDto.getInstrumentType().toString().equals(InstrumentType.CARD.toString())){
            throw new NullPointerException("The instrument Type is not valid for this type Request type");
        }
        User user = userService.getUser(instrumentDto.getUserId());
        user.setUpdatedAt(LocalDateTime.now());

        CardDto cardDto = instrumentDto.getCardDetails();
        CardDetails cardDetails = new CardDetails();
        cardDetails.setCardNumber(cardDto.getCardNumber());
        cardDetails.setCardType(cardDto.getCardType());
        cardDetails.setCvv(cardDto.getCvv());
        cardDetails.setUserName(instrumentDto.getUserName());
        cardDetails.setUser(user);

        CardExpiryDateDto validateAndConvertExpiryDate = validateAndConvertExpiryDate(cardDto.getExpiryDate());
        if(!validateAndConvertExpiryDate.isValid()){
            throw new DateTimeRelatedException("Your Expiry Date is not right , Please check again !");
        }
        cardDetails.setExpiryDate(validateAndConvertExpiryDate.getLocalDate());
        log.info("the card deatils entity saved in db is {}" , cardDetails);
        return this.cardDetailsRepository.save(cardDetails);

    }

    @Override
    public InstrumentDetailsResponse getInstrumentDetailsForUser(Long instrumentId, Long userId, InstrumentType instrumentType) {
        if(!instrumentType.toString().equals(InstrumentType.CARD.toString())){
            throw new NullPointerException("The instrument Type is not valid for this type Request type");
        }

        log.info("Validating the userId : {} if it exists in Database.", userId);
        userService.validateUserIdExists(userId);

        CardDetails cardDetails = this.cardDetailsRepository.findByCardDetailsIdAndUserId(instrumentId, userId).orElseThrow(() ->
        {throw new ResourceNotFoundExcetion("cardDetails", "instrument id and userId", instrumentId);});

        log.info("Card details returned is : {}", cardDetails.getCardNumber());
        InstrumentDetailsResponse instrumentDetailsResponse = InstrumentDetailsResponse.builder().instrumentDetails(cardDetails).user(cardDetails.getUser()).build();
        return instrumentDetailsResponse;
    }

    private CardExpiryDateDto validateAndConvertExpiryDate(String expiryDate){
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth yearMonth = YearMonth.parse(expiryDate, inputFormatter);
            // Convert YearMonth to LocalDate (end of the month)
            LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
            log.info("The expiry date that has finally come after converting is : {}", lastDayOfMonth);
            boolean validationForValidityOfExpiryDate = lastDayOfMonth.isAfter(LocalDate.now());
            if (validationForValidityOfExpiryDate) {
                return CardExpiryDateDto.builder()
                        .valid(true).localDate(lastDayOfMonth).build();
            }
        }catch (DateTimeParseException e) {
            log.info(e.getMessage());
            throw new DateTimeRelatedException(e.getMessage());
        }
        return CardExpiryDateDto.builder()
                .valid(false).localDate(null).build();
    }
}
