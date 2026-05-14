package com.lvwyh.aliyun.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aliyun.swagger-security")
public class SwaggerSecurityProperties {

    /**
     * 是否启用Swagger访问保护
     */
    private Boolean enabled = true;

    /**
     * Swagger登录用户名
     */
    private String username = "admin";

    /**
     * Swagger登录密码
     */
    private String password = "admin";

    /**
     * 允许访问Swagger的IP白名单，多个值用英文逗号分隔，支持单IP、CIDR和*
     */
    private String allowedIps = "127.0.0.1,::1";

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAllowedIps() {
        return allowedIps;
    }

    public void setAllowedIps(String allowedIps) {
        this.allowedIps = allowedIps;
    }
}
