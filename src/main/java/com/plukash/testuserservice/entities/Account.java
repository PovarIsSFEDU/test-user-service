package com.plukash.testuserservice.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PositiveOrZero
    private BigDecimal balance;

    @Positive
    @NotNull
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    @Column(name = "max_balance")
    private BigDecimal maxBalance;

    public Account(BigDecimal balance) {
        this.balance = balance;
        this.maxBalance = balance.multiply(BigDecimal.valueOf(2.07)).setScale(2, RoundingMode.UP);
    }
}
