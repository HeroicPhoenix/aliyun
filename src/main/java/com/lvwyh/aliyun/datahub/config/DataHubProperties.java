package com.lvwyh.aliyun.datahub.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aliyun.datahub")
public class DataHubProperties {

    private String accessKeyId;

    private String accessKeySecret;

    private String endpoint;

    private Integer operationTimeoutMs = 5000;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getOperationTimeoutMs() {
        return operationTimeoutMs;
    }

    public void setOperationTimeoutMs(Integer operationTimeoutMs) {
        this.operationTimeoutMs = operationTimeoutMs;
    }
}
