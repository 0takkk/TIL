package com.example.cleanarchitecture.account.adapter.in.web;

import com.example.cleanarchitecture.account.application.port.in.SendMoneyCommand;
import com.example.cleanarchitecture.account.application.port.in.SendMoneyUseCase;
import com.example.cleanarchitecture.account.domain.Money;
import com.example.cleanarchitecture.account.domain.vo.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SendMoneyController {

    private final SendMoneyUseCase sendMoneyUseCase;

    @PostMapping("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    void sendMoney(
            @PathVariable("sourceAccountId") Long sourceAccountId,
            @PathVariable("targetAccountId") Long targetAccountId,
            @PathVariable("amount") Long amount
    ) {
        SendMoneyCommand command = new SendMoneyCommand(
                new AccountId(sourceAccountId),
                new AccountId(targetAccountId),
                Money.of(amount)
        );

        sendMoneyUseCase.sendMoney(command);
    }
}
