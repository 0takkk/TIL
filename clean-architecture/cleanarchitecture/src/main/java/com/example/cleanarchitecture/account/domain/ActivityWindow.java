package com.example.cleanarchitecture.account.domain;

import com.example.cleanarchitecture.account.domain.vo.AccountId;

import java.util.List;

public class ActivityWindow {

    private List<Activity> activities;

    public Money calculateBalance(AccountId accountId) {
        Money depositeBalance = activities.stream()
                .filter(activity -> activity.getTargetAccountId().equals(accountId))
                .map(Activity::getMoney)
                .reduce(Money.ZERO, Money::add);

        Money withdrawalBalance = activities.stream()
                .filter(activity -> activity.getSourceAccountId().equals(accountId))
                .map(Activity::getMoney)
                .reduce(Money.ZERO, Money::add);

        return Money.add(depositeBalance, withdrawalBalance.negate());
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }
}
