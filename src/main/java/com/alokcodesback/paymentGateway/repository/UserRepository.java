package com.alokcodesback.paymentGateway.repository;

import com.alokcodesback.paymentGateway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
