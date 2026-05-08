package com.lvwyh.aliyun.dataworks.config;

import com.aliyun.dataworks_public20200518.Client;
import com.aliyun.teaopenapi.models.Config;
import com.lvwyh.aliyun.dataworks.config.DataWorksProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataWorksSdkConfig {

    @Bean
    public Client dataWorksClient(DataWorksProperties properties) throws Exception {
        Config config = new Config()
                .setAccessKeyId(properties.getAccessKeyId())
                .setAccessKeySecret(properties.getAccessKeySecret())
                .setRegionId(properties.getRegionId())
                .setEndpoint(properties.getEndpoint());
        return new Client(config);
    }
}
