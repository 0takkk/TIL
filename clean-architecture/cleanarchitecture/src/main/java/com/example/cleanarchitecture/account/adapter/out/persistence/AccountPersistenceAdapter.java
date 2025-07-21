package com.example.cleanarchitecture.account.adapter.out.persistence;

import com.example.cleanarchitecture.account.application.port.out.LoadAccountPort;
import com.example.cleanarchitecture.account.application.port.out.UpdateAccountStatePort;
import com.example.cleanarchitecture.account.domain.Account;
import com.example.cleanarchitecture.account.domain.Activity;
import com.example.cleanarchitecture.account.domain.ActivityWindow;
import com.example.cleanarchitecture.account.domain.vo.AccountId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {

    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;


    @Override
    public Account loadAccount(AccountId accountId, LocalDateTime baselineDate) {
        AccountJpaEntity accountJpaEntity = accountRepository.findById(accountId.value())
                .orElseThrow(EntityNotFoundException::new);

        List<ActivityJpaEntity> activityJpaEntities = activityRepository.findByOwnerSince(accountId.value(), baselineDate);

        Long withdrawalBalance = activityRepository.getWithdrawalBalanceUntil(accountId.value(), baselineDate);
        withdrawalBalance = this.orZero(withdrawalBalance);

        Long depositBalance = activityRepository.getDepositBalanceUntil(accountId.value(), baselineDate);
        depositBalance = this.orZero(depositBalance);

        return accountMapper.mapToDomainEntity(accountJpaEntity, activityJpaEntities, withdrawalBalance, depositBalance);
    }

    @Override
    public void updateActivities(Account account) {
        ActivityWindow activityWindow = account.getActivityWindow();
        List<Activity> activities = activityWindow.getActivities();

        for (Activity activity : activities) {
            if(activity.getId().isEmpty()) {
                activityRepository.save(accountMapper.mapToJpaEntity(activity));
            }
        }
    }

    private Long orZero(Long value){
        return value == null ? 0L : value;
    }
}
