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

    /**
     * 是否启用DataHub Kafka兼容模式
     */
    private Boolean datahubCompatibleEnabled = false;

    /**
     * DataHub Kafka兼容模式AccessKey ID
     */
    private String accessKeyId;

    /**
     * DataHub Kafka兼容模式AccessKey Secret
     */
    private String accessKeySecret;

    /**
     * JAAS配置文件路径，配置后优先使用该文件
     */
    private String jaasConfigPath;

    /**
     * 是否启用SSL truststore文件
     */
    private Boolean truststoreEnabled = false;

    /**
     * SSL truststore文件路径
     */
    private String truststoreLocation;

    /**
     * SSL truststore密码
     */
    private String truststorePassword;

    /**
     * SSL endpoint校验算法，内网证书域名不匹配时可配置为空字符串
     */
    private String sslEndpointIdentificationAlgorithm;

    /**
     * 生产消息压缩类型
     */
    private String compressionType = "lz4";

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

    public Boolean getDatahubCompatibleEnabled() {
        return datahubCompatibleEnabled;
    }

    public void setDatahubCompatibleEnabled(Boolean datahubCompatibleEnabled) {
        this.datahubCompatibleEnabled = datahubCompatibleEnabled;
    }

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

    public String getJaasConfigPath() {
        return jaasConfigPath;
    }

    public void setJaasConfigPath(String jaasConfigPath) {
        this.jaasConfigPath = jaasConfigPath;
    }

    public Boolean getTruststoreEnabled() {
        return truststoreEnabled;
    }

    public void setTruststoreEnabled(Boolean truststoreEnabled) {
        this.truststoreEnabled = truststoreEnabled;
    }

    public String getTruststoreLocation() {
        return truststoreLocation;
    }

    public void setTruststoreLocation(String truststoreLocation) {
        this.truststoreLocation = truststoreLocation;
    }

    public String getTruststorePassword() {
        return truststorePassword;
    }

    public void setTruststorePassword(String truststorePassword) {
        this.truststorePassword = truststorePassword;
    }

    public String getSslEndpointIdentificationAlgorithm() {
        return sslEndpointIdentificationAlgorithm;
    }

    public void setSslEndpointIdentificationAlgorithm(String sslEndpointIdentificationAlgorithm) {
        this.sslEndpointIdentificationAlgorithm = sslEndpointIdentificationAlgorithm;
    }

    public String getCompressionType() {
        return compressionType;
    }

    public void setCompressionType(String compressionType) {
        this.compressionType = compressionType;
    }
}
