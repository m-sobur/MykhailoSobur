package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.interfaces.Validator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(bean.getClass().getSimpleName() + " BeanPostProcessor.postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(bean.getClass().getSimpleName() + " BeanPostProcessor.postProcessAfterInitialization");
        if (bean instanceof Validator) {
            ((Validator) bean).validate();
        }
        return bean;
    }
}