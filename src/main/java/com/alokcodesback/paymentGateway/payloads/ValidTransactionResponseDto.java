package com.alokcodesback.paymentGateway.payloads;


import com.alokcodesback.paymentGateway.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ValidTransactionResponseDto {
    boolean  valid;
    User senderUser;
    User receiverUser;
}
