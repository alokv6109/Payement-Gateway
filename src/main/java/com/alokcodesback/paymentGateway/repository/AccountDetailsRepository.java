package com.alokcodesback.paymentGateway.repository;

import com.alokcodesback.paymentGateway.entity.AccountDetails;
import com.alokcodesback.paymentGateway.entity.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountDetailsRepository extends JpaRepository<AccountDetails , Long> {

        @Query("select a from AccountDetails a where a.id = :accountDetailsId and a.user.id =:userId ")
        Optional<AccountDetails> findByAccountDetailsIdAndUserId(@Param("accountDetailsId") Long accountDetailsId,
                                                                 @Param("userId") Long userId);

}
