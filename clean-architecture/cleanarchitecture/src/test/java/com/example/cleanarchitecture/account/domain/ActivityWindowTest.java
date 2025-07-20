package com.example.cleanarchitecture.account.domain;

import com.example.cleanarchitecture.account.domain.vo.AccountId;
import com.example.cleanarchitecture.common.ActivityTestData;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ActivityWindowTest {

    @Test
    void calculateBalance() {
        AccountId accountId1 = new AccountId(1L);
        AccountId accountId2 = new AccountId(2L);

        ActivityWindow activityWindow = new ActivityWindow(
                ActivityTestData.defaultActivity()
                        .withSourceAccount(accountId1)
                        .withTargetAccount(accountId2)
                        .withMoney(Money.of(999L))
                        .build(),
                ActivityTestData.defaultActivity()
                        .withSourceAccount(accountId1)
                        .withTargetAccount(accountId2)
                        .withMoney(Money.of(1L))
                        .build(),
                ActivityTestData.defaultActivity()
                        .withSourceAccount(accountId2)
                        .withTargetAccount(accountId1)
                        .withMoney(Money.of(500L))
                        .build()
        );

        assertThat(activityWindow.calculateBalance(accountId1)).isEqualTo(Money.of(-500L));
        assertThat(activityWindow.calculateBalance(accountId2)).isEqualTo(Money.of(500L));
    }
}
