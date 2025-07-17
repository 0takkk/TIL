package com.example.cleanarchitecture.account.application.port.out;

import com.example.cleanarchitecture.account.domain.Account;
import com.example.cleanarchitecture.account.domain.vo.AccountId;

import java.time.LocalDateTime;

public interface LoadAccountPort {

    Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
