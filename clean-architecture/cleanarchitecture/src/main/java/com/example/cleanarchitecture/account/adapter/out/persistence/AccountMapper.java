package com.example.cleanarchitecture.account.adapter.out.persistence;

import com.example.cleanarchitecture.account.domain.Account;
import com.example.cleanarchitecture.account.domain.Activity;
import com.example.cleanarchitecture.account.domain.ActivityWindow;
import com.example.cleanarchitecture.account.domain.Money;
import com.example.cleanarchitecture.account.domain.vo.AccountId;
import com.example.cleanarchitecture.account.domain.vo.ActivityId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class AccountMapper {

    Account mapToDomainEntity(
            AccountJpaEntity account,
            List<ActivityJpaEntity> activities,
            Long withdrawalBalance,
            Long depositBalance
    ) {
        Money baselineBalance = Money.subtract(
                Money.of(depositBalance),
                Money.of(withdrawalBalance)
        );

        return Account.withId(
                new AccountId(account.getId()),
                baselineBalance,
                this.mapToActivityWindow(activities)
        );
    }

    ActivityWindow mapToActivityWindow(List<ActivityJpaEntity> activities) {
        List<Activity> mappedActivities = new ArrayList<>();

        for (ActivityJpaEntity activity : activities) {
            mappedActivities.add(
                    new Activity(
                            new ActivityId(activity.getId()),
                            new AccountId(activity.getOwnerAccountId()),
                            new AccountId(activity.getSourceAccountId()),
                            new AccountId(activity.getTargetAccountId()),
                            activity.getTimestamp(),
                            Money.of(activity.getAmount())
                    )
            );
        }

        return new ActivityWindow(mappedActivities);
    }

    ActivityJpaEntity mapToJpaEntity(Activity activity) {
        return new ActivityJpaEntity(
                activity.getId() == null ? null : activity.getId().value(),
                activity.getTimestamp(),
                activity.getOwnerAccountId().value(),
                activity.getSourceAccountId().value(),
                activity.getTargetAccountId().value(),
                activity.getMoney().getAmount().longValue()
        );
    }
}
