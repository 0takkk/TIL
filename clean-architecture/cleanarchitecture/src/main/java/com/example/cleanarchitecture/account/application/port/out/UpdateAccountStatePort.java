package com.example.cleanarchitecture.account.application.port.out;

import com.example.cleanarchitecture.account.domain.Account;

public interface UpdateAccountStatePort {

    void updateActivities(Account account);
}
