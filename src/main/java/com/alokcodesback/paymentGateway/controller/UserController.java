package com.alokcodesback.paymentGateway.controller;

import com.alokcodesback.paymentGateway.entity.User;
import com.alokcodesback.paymentGateway.payloads.UserDto;
import com.alokcodesback.paymentGateway.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto user){
        User user1 = this.userService.addUser(user);
        return ResponseEntity.ok(user1);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<User> getSingleUser(@PathVariable("userID") long userId){
        User user = this.userService.getUser(userId);
        return ResponseEntity.ok(user);
    }



}
