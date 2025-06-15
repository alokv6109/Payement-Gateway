package com.alokcodesback.paymentGateway.controller;

import com.alokcodesback.paymentGateway.config.InstrumentServiceFactory;
import com.alokcodesback.paymentGateway.entity.InstrumentDetails;
import com.alokcodesback.paymentGateway.entity.User;
import com.alokcodesback.paymentGateway.exception.InstrumentTypeException;
import com.alokcodesback.paymentGateway.exception.ResourceNotFoundExcetion;
import com.alokcodesback.paymentGateway.payloads.ApiResponse;
import com.alokcodesback.paymentGateway.payloads.InstrumentDetailsResponse;
import com.alokcodesback.paymentGateway.payloads.InstrumentDto;
import com.alokcodesback.paymentGateway.payloads.enums.InstrumentType;
import com.alokcodesback.paymentGateway.service.InstrumentService;
import com.alokcodesback.paymentGateway.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/instruments")
@Slf4j
public class InstrumentsController {

    private final InstrumentServiceFactory instrumentServiceFactory;

    @Autowired
    public InstrumentsController(InstrumentServiceFactory instrumentServiceFactory) {
        this.instrumentServiceFactory = instrumentServiceFactory;
    }

    @PostMapping("/")
    public ResponseEntity<InstrumentDetails> createInstrument(@Valid @RequestBody InstrumentDto instrumentDto){
        log.info("the instrument object passed from the client is {} ", instrumentDto);
        InstrumentService service = instrumentServiceFactory.getService(instrumentDto.getInstrumentType());
        if (service == null) {
            log.info("the instrument type did not got verified instrument Type is : {}", instrumentDto.getInstrumentType());
            throw new InstrumentTypeException("The instrument type is not correct , found type = " + instrumentDto.getInstrumentType());
        }
        InstrumentDetails instrumentDetails = service.addInstrument(instrumentDto);
        return new ResponseEntity<>(instrumentDetails, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<InstrumentDetailsResponse> getInstrumentForUser(@RequestParam Long instrumentId,
                                                                          @RequestParam Long userId,
                                                                          @RequestParam String instrumentType){
        InstrumentType instrumentType1 = InstrumentType.fromValue(instrumentType);
        InstrumentService service = instrumentServiceFactory.getService(instrumentType1);
        if (service == null) {
            log.info("the instrument type did not got verified instrument Type is : {}", instrumentType1);
            throw new InstrumentTypeException("The instrument type is not correct , found type = " + instrumentType1);
        }

        InstrumentDetailsResponse instrumentDetails = service.getInstrumentDetailsForUser(instrumentId, userId, instrumentType1);
        return ResponseEntity.ok(instrumentDetails);
    }

}
