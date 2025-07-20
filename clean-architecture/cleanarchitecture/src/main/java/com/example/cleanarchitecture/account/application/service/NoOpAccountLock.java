package com.example.cleanarchitecture.account.application.service;

import com.example.cleanarchitecture.account.application.port.out.AccountLock;
import com.example.cleanarchitecture.account.domain.vo.AccountId;
import org.springframework.stereotype.Component;

@Component
class NoOpAccountLock implements AccountLock {

	@Override
	public void lockAccount(AccountId accountId) {
		// do nothing
	}

	@Override
	public void releaseAccount(AccountId accountId) {
		// do nothing
	}

}
