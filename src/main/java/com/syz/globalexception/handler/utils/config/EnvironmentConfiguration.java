package com.syz.globalexception.handler.utils.config;

import com.syz.globalexception.handler.utils.EnvContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
@RefreshScope
public class EnvironmentConfiguration {
    @Autowired
    private Environment environment;

    public EnvironmentConfiguration() {
    }

    @Bean
    public EnvContext environmentContext() {
        return new EnvContext(this.environment);
    }
}