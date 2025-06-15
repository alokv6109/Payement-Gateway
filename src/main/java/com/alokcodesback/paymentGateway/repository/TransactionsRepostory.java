package com.alokcodesback.paymentGateway.repository;

import com.alokcodesback.paymentGateway.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepostory extends JpaRepository<Transactions, Long> {
}
