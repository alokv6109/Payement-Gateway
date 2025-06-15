package com.alokcodesback.paymentGateway.service.impl;

import com.alokcodesback.paymentGateway.entity.User;
import com.alokcodesback.paymentGateway.payloads.UserDto;
import com.alokcodesback.paymentGateway.exception.ResourceNotFoundExcetion;
import com.alokcodesback.paymentGateway.repository.UserRepository;
import com.alokcodesback.paymentGateway.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(UserDto user) {
        User user1 = new User();
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());

        log.info("add user service with user : {}", user1);
        User save = this.userRepository.save(user1);
        return save;
    }

    @Override
    public User getUser(long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExcetion("user", "id", id));
        return user;
    }

    @Override
    public boolean validateUserIdExists(long id) {
        boolean exists = this.userRepository.existsById(id);
        if(!exists){
            throw new ResourceNotFoundExcetion("user", "id", id);
        }
        log.info("The user ID : {} exists : {} in the users table.", id, exists);
        return exists;
    }
}
