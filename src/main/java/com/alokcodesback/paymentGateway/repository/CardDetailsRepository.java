package com.alokcodesback.paymentGateway.repository;

import com.alokcodesback.paymentGateway.entity.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CardDetailsRepository extends JpaRepository<CardDetails, Long> {

    @Query("select c from CardDetails c where c.id = :cardDetailsId and c.user.id = :userId")
    Optional<CardDetails> findByCardDetailsIdAndUserId(@Param("cardDetailsId") Long cardDetailsId,
                                                       @Param("userId") Long userId);
}
