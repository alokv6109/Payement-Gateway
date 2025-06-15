package com.alokcodesback.paymentGateway.entity;

import com.alokcodesback.paymentGateway.payloads.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
public class CardDetails extends InstrumentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    @Size(min = 16, max = 16, message = "card number must be 16 characters")
    private String cardNumber;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CardType cardType; // e.g., Credit, Debit


    @Column(nullable = false)
    private LocalDate expiryDate;


    @Column(nullable = false)
    private Integer cvv;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;


    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();
}
