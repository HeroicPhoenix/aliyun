package com.lvwyh.aliyun.config;

import com.lvwyh.aliyun.security.SwaggerSecurityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerSecurityConfig {

    @Bean
    public FilterRegistrationBean<SwaggerSecurityFilter> swaggerSecurityFilterRegistration(
            SwaggerSecurityFilter swaggerSecurityFilter) {
        FilterRegistrationBean<SwaggerSecurityFilter> registrationBean =
                new FilterRegistrationBean<SwaggerSecurityFilter>();
        registrationBean.setFilter(swaggerSecurityFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
