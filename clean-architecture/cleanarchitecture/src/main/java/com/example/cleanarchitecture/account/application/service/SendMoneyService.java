package com.example.cleanarchitecture.account.application.service;

import com.example.cleanarchitecture.account.application.port.in.SendMoneyCommand;
import com.example.cleanarchitecture.account.application.port.in.SendMoneyUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class SendMoneyService implements SendMoneyUseCase {

    private final LoadAccountPort loadAccountPort;
    private final AccountLock accountLock;
    private final UpdateAccountStatePort updateAccountStatePort;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        // TODO : 비즈니스 규칙 검증
        // TODO : 모델 상태 조작
        return false;
    }
}
