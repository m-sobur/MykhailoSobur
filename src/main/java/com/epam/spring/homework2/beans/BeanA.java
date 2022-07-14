package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.interfaces.Validator;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanA implements InitializingBean, DisposableBean, Validator {
    private String name;
    private int value;

    public BeanA(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.getClass().getSimpleName() + " InitializingBean.afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(this.getClass().getSimpleName() + " DisposableBean.destroy");

    }

    @Override
    public void validate() {
        System.out.println(this.getClass().getSimpleName() + " Inside validate method");
        if (name != null && value >= 0) {
            System.out.println("Validation result for " + this.getClass().getSimpleName() + " is positive");
        } else
            System.out.println("Validation result for " + this.getClass().getSimpleName() + " is negative");
    }
}
