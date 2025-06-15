package com.alokcodesback.paymentGateway.service;

import com.alokcodesback.paymentGateway.entity.User;
import com.alokcodesback.paymentGateway.payloads.UserDto;

public interface UserService {

    User addUser(UserDto user);

    User getUser(long id);

    boolean validateUserIdExists(long id);
}
