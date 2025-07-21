package com.example.cleanarchitecture.account.adapter.out.persistence;

import com.example.cleanarchitecture.account.domain.Account;
import com.example.cleanarchitecture.account.domain.ActivityWindow;
import com.example.cleanarchitecture.account.domain.Money;
import com.example.cleanarchitecture.account.domain.vo.AccountId;
import com.example.cleanarchitecture.common.AccountTestData;
import com.example.cleanarchitecture.common.ActivityTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({
        AccountPersistenceAdapter.class,
        AccountMapper.class
})
class AccountPersistenceAdapterTest {

    @Autowired
    private AccountPersistenceAdapter adapter;

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    @Sql("AccountPersistenceAdapterTest.sql")
    void loadAccount() {
        Account account = adapter.loadAccount(
                new AccountId(1L),
                LocalDateTime.of(2025, 7, 1, 0, 0, 0)
        );

        assertThat(account.getActivityWindow().getActivities()).hasSize(2);
        assertThat(account.calculateBalance()).isEqualTo(Money.of(500));
    }

    @Test
    void updateActivities() {
        Account account = AccountTestData.defaultAccount()
                .withBaselineBalance(Money.of(555L))
                .withActivityWindow(
                        new ActivityWindow(
                                ActivityTestData.defaultActivity()
                                        .withId(null)
                                        .withMoney(Money.of(1L))
                                        .build()
                        )
                )
                .build();

        adapter.updateActivities(account);

        assertThat(activityRepository.count()).isEqualTo(1);

        ActivityJpaEntity savedActivity = activityRepository.findAll().get(0);
        assertThat(savedActivity.getAmount()).isEqualTo(1L);
    }

}