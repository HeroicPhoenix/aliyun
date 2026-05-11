package com.lvwyh.aliyun.maxcompute.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aliyun.maxcompute")
public class MaxComputeProperties {

    /**
     * 阿里云 AccessKey ID
     */
    private String accessKeyId;

    /**
     * 阿里云 AccessKey Secret
     */
    private String accessKeySecret;

    /**
     * MaxCompute endpoint
     */
    private String endpoint;

    /**
     * CSV导出目录，容器内路径
     */
    private String exportDir;

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

    public String getExportDir() {
        return exportDir;
    }

    public void setExportDir(String exportDir) {
        this.exportDir = exportDir;
    }
}
