package com.example.cleanarchitecture.account.domain;

import com.example.cleanarchitecture.account.domain.vo.AccountId;
import com.example.cleanarchitecture.account.domain.vo.ActivityId;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

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
            @NonNull AccountId ownerAccountId,
            @NonNull AccountId sourceAccountId,
            @NonNull AccountId targetAccountId,
            @NonNull LocalDateTime timestamp,
            @NonNull Money money
    ) {
        this.id = ActivityId.empty();
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.timestamp = timestamp;
        this.money = money;
    }

    public Activity(
            ActivityId id,
            @NonNull AccountId ownerAccountId,
            @NonNull AccountId sourceAccountId,
            @NonNull AccountId targetAccountId,
            @NonNull LocalDateTime timestamp,
            @NonNull Money money
    ) {
        this.id = id;
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.timestamp = timestamp;
        this.money = money;
    }
}
