package com.lvwyh.aliyun.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aliyun.kafka")
public class KafkaProperties {

    /**
     * Kafka bootstrap servers，例如 127.0.0.1:9092
     */
    private String bootstrapServers;

    /**
     * Kafka单次操作超时时间，单位毫秒
     */
    private Integer operationTimeoutMs = 5000;

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public Integer getOperationTimeoutMs() {
        return operationTimeoutMs;
    }

    public void setOperationTimeoutMs(Integer operationTimeoutMs) {
        this.operationTimeoutMs = operationTimeoutMs;
    }
}
