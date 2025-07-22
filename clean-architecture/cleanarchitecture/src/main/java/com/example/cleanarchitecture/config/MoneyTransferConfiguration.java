package com.example.cleanarchitecture.config;

import com.example.cleanarchitecture.account.application.service.MoneyTransferProperties;
import com.example.cleanarchitecture.account.domain.Money;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MoneyTransferConfigurationProperties.class)
public class MoneyTransferConfiguration {

    @Bean
    public MoneyTransferProperties moneyTransferProperties(MoneyTransferConfigurationProperties properties) {
        return new MoneyTransferProperties(Money.of(properties.getTransferThreshold()));
    }
}
