package com.syz.globalexception.handler.utils.config;

import com.syz.globalexception.handler.utils.SpringUtil;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class DefApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public DefApplicationContextInitializer() {
    }

    public void initialize(ConfigurableApplicationContext applicationContext) {
        BeanDefinitionRegistry beanDefinitionRegistry = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SpringUtil.class);
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition("springUtil", beanDefinition);
    }
}
