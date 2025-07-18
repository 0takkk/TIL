package com.example.cleanarchitecture.account.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityJpaEntity, Long> {

    @Query("select a from ActivityJpaEntity a where a.ownerAccountId = :ownerAccountId and a.timestamp >= :since")
    List<ActivityJpaEntity> findByOwnerSince(Long ownerAccountId, LocalDateTime since);

    @Query("select sum(a.amount) from ActivityJpaEntity a " +
            "where a.targetAccountId = :accountId " +
            "and a.ownerAccountId = :accountId " +
            "and a.timestamp < :until")
    Long getDepositBalanceUntil(Long accountId, LocalDateTime until);

    @Query("select sum(a.amount) from ActivityJpaEntity a " +
            "where a.sourceAccountId = :accountId " +
            "and a.ownerAccountId = :accountId " +
            "and a.timestamp < :until")
    Long getWithdrawalBalanceUntil(Long accountId, LocalDateTime until);
}
