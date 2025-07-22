package com.example.cleanarchitecture.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "cleanarchitecture")
public class MoneyTransferConfigurationProperties {

    private long transferThreshold = Long.MAX_VALUE;
}
