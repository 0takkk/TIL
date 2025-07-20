package com.example.cleanarchitecture.account.application.service;

import com.example.cleanarchitecture.account.application.port.in.SendMoneyCommand;
import com.example.cleanarchitecture.account.application.port.out.AccountLock;
import com.example.cleanarchitecture.account.application.port.out.LoadAccountPort;
import com.example.cleanarchitecture.account.application.port.out.UpdateAccountStatePort;
import com.example.cleanarchitecture.account.domain.Account;
import com.example.cleanarchitecture.account.domain.Money;
import com.example.cleanarchitecture.account.domain.vo.AccountId;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class SendMoneyServiceTest {

    private final LoadAccountPort loadAccountPort = Mockito.mock(LoadAccountPort.class);

    private final AccountLock accountLock = Mockito.mock(AccountLock.class);

    private final UpdateAccountStatePort updateAccountStatePort = Mockito.mock(UpdateAccountStatePort.class);

    private final SendMoneyService sendMoneyService =
            new SendMoneyService(loadAccountPort, accountLock, updateAccountStatePort, this.moneyTransferProperties());

    @Test
    void transactionSucceeds() {
        Account sourceAccount = this.givenSourceAccount();
        Account targetAccount = this.givenTargetAccount();

        this.givenWithdrawalWillSucceed(sourceAccount);
        this.givenDepositWillSucceed(targetAccount);

        Money money = Money.of(500L);

        SendMoneyCommand command = new SendMoneyCommand(
                sourceAccount.getId().get(),
                targetAccount.getId().get(),
                money
        );

        boolean success = sendMoneyService.sendMoney(command);

        assertThat(success).isTrue();

        AccountId sourceAccountId = sourceAccount.getId().get();
        AccountId targetAccountId = targetAccount.getId().get();

        then(accountLock).should().lockAccount(eq(sourceAccountId));
        then(sourceAccount).should().withdraw(eq(money), eq(targetAccountId));
        then(accountLock).should().releaseAccount(eq(sourceAccountId));

        then(accountLock).should().lockAccount(eq(targetAccountId));
        then(targetAccount).should().deposit(eq(money), eq(sourceAccountId));
        then(accountLock).should().releaseAccount(eq(targetAccountId));

        this.thenAccountsHaveBeenUpdated(sourceAccountId, targetAccountId);
    }

    @Test
    void givenWithdrawalFails_thenOnlySourceAccountIsLockedAndReleased() {
        AccountId sourceAccountId = new AccountId(41L);
        Account sourceAccount = this.givenAnAccountWithId(sourceAccountId);

        AccountId targetAccountId = new AccountId(42L);
        Account targetAccount = this.givenAnAccountWithId(targetAccountId);

        this.givenWithdrawalWillFail(sourceAccount);
        this.givenDepositWillSucceed(targetAccount);

        SendMoneyCommand command = new SendMoneyCommand(
                sourceAccountId,
                targetAccountId,
                Money.of(300L)
        );

        boolean success = sendMoneyService.sendMoney(command);

        assertThat(success).isFalse();

        then(accountLock).should().lockAccount(eq(sourceAccountId));
        then(accountLock).should().releaseAccount(eq(sourceAccountId));

        then(accountLock).should(times(0)).lockAccount(eq(targetAccountId));
    }

    private Account givenSourceAccount() {
        return this.givenAnAccountWithId(new AccountId(1L));
    }

    private Account givenTargetAccount() {
        return this.givenAnAccountWithId(new AccountId(2L));
    }

    private Account givenAnAccountWithId(AccountId id) {
        Account account = Mockito.mock(Account.class);

        given(account.getId())
                .willReturn(Optional.of(id));

        given(loadAccountPort.loadAccount(eq(account.getId().get()), any(LocalDateTime.class)))
                .willReturn(account);

        return account;
    }

    private void givenWithdrawalWillSucceed(Account account) {
        given(account.withdraw(any(Money.class), any(AccountId.class)))
                .willReturn(true);
    }

    private void givenDepositWillSucceed(Account account) {
        given(account.deposit(any(Money.class), any(AccountId.class)))
                .willReturn(true);
    }

    private void givenWithdrawalWillFail(Account account) {
        given(account.withdraw(any(Money.class), any(AccountId.class)))
                .willReturn(false);
    }

    private void thenAccountsHaveBeenUpdated(AccountId... accountIds) {
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        then(updateAccountStatePort).should(times(accountIds.length))
                .updateActivities(accountCaptor.capture());

        List<AccountId> updatedAccountIds = accountCaptor.getAllValues()
                .stream()
                .map(Account::getId)
                .map(Optional::get)
                .toList();

        for (AccountId accountId : accountIds) {
            assertThat(updatedAccountIds).contains(accountId);
        }
    }

    private MoneyTransferProperties moneyTransferProperties(){
        return new MoneyTransferProperties(Money.of(Long.MAX_VALUE));
    }
}