package com.example.cleanarchitecture.account.domain;

import com.example.cleanarchitecture.account.domain.vo.AccountId;
import com.example.cleanarchitecture.account.domain.vo.ActivityId;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Getter
public class Activity {

    private ActivityId id;

    @NonNull
    private final AccountId ownerAccountId;

    @NonNull
    private final AccountId sourceAccountId;

    @NonNull
    private final AccountId targetAccountId;

    @NonNull
    private final LocalDateTime timestamp;

    @NonNull
    private final Money money;

    public Activity(
            AccountId ownerAccountId,
            AccountId sourceAccountId,
            AccountId targetAccountId,
            LocalDateTime timestamp,
            Money money
    ) {
        this.id = ActivityId.empty();
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.timestamp = timestamp;
        this.money = money;
    }
}
