package com.lvwyh.aliyun.maxcompute.config;

import com.aliyun.odps.Odps;
import com.aliyun.odps.account.Account;
import com.aliyun.odps.account.AliyunAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MaxComputeSdkConfig {

    @Bean
    public Odps maxComputeOdps(MaxComputeProperties properties) {
        Account account = new AliyunAccount(properties.getAccessKeyId(), properties.getAccessKeySecret());
        Odps odps = new Odps(account);
        odps.setEndpoint(properties.getEndpoint());
        return odps;
    }
}
