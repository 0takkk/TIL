package com.example.cleanarchitecture.common;

import com.example.cleanarchitecture.account.domain.Account;
import com.example.cleanarchitecture.account.domain.ActivityWindow;
import com.example.cleanarchitecture.account.domain.Money;
import com.example.cleanarchitecture.account.domain.vo.AccountId;

public class AccountTestData {

	public static AccountBuilder defaultAccount() {
		return new AccountBuilder()
				.withAccountId(new AccountId(1L))
				.withBaselineBalance(Money.of(999L))
				.withActivityWindow(
						new ActivityWindow(
								ActivityTestData.defaultActivity().build(),
								ActivityTestData.defaultActivity().build()
						)
				);
	}

	public static class AccountBuilder {

		private AccountId accountId;
		private Money baselineBalance;
		private ActivityWindow activityWindow;

		public AccountBuilder withAccountId(AccountId accountId) {
			this.accountId = accountId;
			return this;
		}

		public AccountBuilder withBaselineBalance(Money baselineBalance) {
			this.baselineBalance = baselineBalance;
			return this;
		}

		public AccountBuilder withActivityWindow(ActivityWindow activityWindow) {
			this.activityWindow = activityWindow;
			return this;
		}

		public Account build() {
			return Account.withId(this.accountId, this.baselineBalance, this.activityWindow);
		}

	}


}
