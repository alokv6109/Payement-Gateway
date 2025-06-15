package com.alokcodesback.paymentGateway.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class UserDto {

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    @NotBlank
    private String email;

}
