package com.example.cleanarchitecture.account.application.service;

import com.example.cleanarchitecture.account.domain.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MoneyTransferProperties {

    private Money maximumTransferThreshold = Money.of(1_000_000L);

}
