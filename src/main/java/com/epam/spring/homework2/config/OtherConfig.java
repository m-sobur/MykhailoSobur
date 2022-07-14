package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class OtherConfig {

    @Bean
    public BeanA beanA() {
        return new BeanA("beanA", 1);
    }

    @Bean
    public BeanE beanE() {
        return new BeanE("beanE", 2);
    }

    @Bean
    @Lazy
    public BeanF beanF() {
        return new BeanF("beanF", 3);
    }

    @Bean
    public CustomBeanPostProcessor customBeanPostProcessor() {
        return new CustomBeanPostProcessor();
    }

    @Bean
    public static MyBeanFactoryPostProcessor customBeanFactoryPostProcessor() {
        return new MyBeanFactoryPostProcessor();
    }
}
