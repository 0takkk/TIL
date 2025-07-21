package com.example.cleanarchitecture;


import com.example.cleanarchitecture.account.application.port.out.LoadAccountPort;
import com.example.cleanarchitecture.account.domain.Account;
import com.example.cleanarchitecture.account.domain.Money;
import com.example.cleanarchitecture.account.domain.vo.AccountId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SendMoneySystemTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LoadAccountPort loadAccountPort;

    @Test
    @Sql("SendMoneySystemTest.sql")
    void sendMoney() {
        Money initialSourceBalance = this.sourceAccount().calculateBalance();
        Money initialTargetBalance = this.targetAccount().calculateBalance();

        ResponseEntity response = this.whenSendMoney(
                this.sourceAccountId(),
                this.targetAccountId(),
                this.transferredAmount()
        );

        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(this.sourceAccount().calculateBalance())
                .isEqualTo(initialSourceBalance.minus(this.transferredAmount()));
        then(this.targetAccount().calculateBalance())
                .isEqualTo(initialTargetBalance.plus(this.transferredAmount()));
    }

    private Account sourceAccount() {
        return this.loadAccount(this.sourceAccountId());
    }

    private Account targetAccount() {
        return this.loadAccount(this.targetAccountId());
    }

    private Account loadAccount(AccountId accountId) {
        return loadAccountPort.loadAccount(
                accountId,
                LocalDateTime.now()
        );
    }

    private AccountId sourceAccountId() {
        return new AccountId(1L);
    }

    private AccountId targetAccountId() {
        return new AccountId(2L);
    }

    private ResponseEntity whenSendMoney(
            AccountId sourceAccountId,
            AccountId targetAccountId,
            Money amount
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Void> request = new HttpEntity<>(null, headers);

        return restTemplate.exchange(
                "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}",
                HttpMethod.POST,
                request,
                Object.class,
                sourceAccountId.value(),
                targetAccountId.value(),
                amount.getAmount()
        );
    }

    private Money transferredAmount() {
        return Money.of(500L);
    }
}
