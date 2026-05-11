package com.lvwyh.aliyun.datahub.config;

import com.aliyun.datahub.client.DatahubClient;
import com.aliyun.datahub.client.DatahubClientBuilder;
import com.aliyun.datahub.client.auth.Account;
import com.aliyun.datahub.client.auth.AliyunAccount;
import com.aliyun.datahub.client.common.DatahubConfig;
import com.aliyun.datahub.client.http.HttpConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataHubSdkConfig {

    @Bean
    public DatahubClient dataHubClient(DataHubProperties properties) {
        Account account = new AliyunAccount(properties.getAccessKeyId(), properties.getAccessKeySecret());
        DatahubConfig datahubConfig = new DatahubConfig(properties.getEndpoint(), account);
        HttpConfig httpConfig = new HttpConfig()
                .setConnTimeout(getOperationTimeoutMs(properties))
                .setReadTimeout(getOperationTimeoutMs(properties))
                .setMaxRetryCount(0);
        return DatahubClientBuilder.newBuilder()
                .setDatahubConfig(datahubConfig)
                .setHttpConfig(httpConfig)
                .build();
    }

    private int getOperationTimeoutMs(DataHubProperties properties) {
        if (properties.getOperationTimeoutMs() == null || properties.getOperationTimeoutMs() < 1000) {
            return 5000;
        }
        return properties.getOperationTimeoutMs();
    }
}
