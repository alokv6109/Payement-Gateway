package com.alokcodesback.paymentGateway.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {

    private String message;
    private boolean success;
    private LocalDateTime localDateTime;

    public ApiResponse(String message, boolean b) {
        this.message = message;
        this.success = b;
        this.localDateTime = LocalDateTime.now();
    }
}
