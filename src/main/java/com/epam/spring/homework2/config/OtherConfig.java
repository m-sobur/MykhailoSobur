package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanF;
import com.epam.spring.homework2.beans.CustomBeanPostProcessor;
import com.epam.spring.homework2.beans.MyBeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class OtherConfig {

    @Bean
    @Lazy
    public BeanF beanF() {
        return new BeanF();
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
