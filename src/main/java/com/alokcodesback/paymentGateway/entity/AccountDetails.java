package com.alokcodesback.paymentGateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="account_details")
public class AccountDetails extends InstrumentDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Account number can not be blank")
    @Column(nullable = false)
    @Size(min = 12, max = 16, message = "acc number must be 12 to 16 character long")
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String bankName;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String userName;


    private String ifscCode;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();
}
