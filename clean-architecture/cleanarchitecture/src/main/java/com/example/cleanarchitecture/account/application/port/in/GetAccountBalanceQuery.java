package com.example.cleanarchitecture.account.application.port.in;

import com.example.cleanarchitecture.account.domain.Money;
import com.example.cleanarchitecture.account.domain.vo.AccountId;

public interface GetAccountBalanceQuery {

    Money getAccountBalance(AccountId accountId);
}
