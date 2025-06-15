package com.alokcodesback.paymentGateway.entity;

import com.alokcodesback.paymentGateway.payloads.enums.InstrumentType;
import com.alokcodesback.paymentGateway.payloads.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotEmpty
    @Column(nullable = false)
    private Double amount;

    @NotEmpty
    @PrimaryKeyJoinColumn
    @OneToOne
    @JsonIgnore
    private User sender;

    @NotEmpty
    @PrimaryKeyJoinColumn
    @OneToOne
    @JsonIgnore
    private User receiver;


    @NotEmpty
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InstrumentType debitInstrumentType;


    @NotEmpty
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InstrumentType creditInstrumentType;

    @NotEmpty
    @Column(nullable = false)
    private Long senderInstrumentId;

    @NotEmpty
    @Column(nullable = false)
    private Long receiverInstrumentId;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();


}
