package com.example.cleanarchitecture.account.application.port.out;

import com.example.cleanarchitecture.account.domain.vo.AccountId;

public interface AccountLock {

    void lockAccount(AccountId accountId);

    void releaseAccount(AccountId accountId);
}
