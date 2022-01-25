package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.interfaces.Validator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanE implements Validator {
    private String name;
    private int value;

    @PostConstruct
    public void postConstructMethod(){
        System.out.println(this.getClass().getSimpleName() + " postConstructMethod");
    }

    @PreDestroy
    public void preDestroyMethod(){
        System.out.println(this.getClass().getSimpleName() + " preDestroyMethod");
    }

    @Override
    public String toString() {
        return "BeanE{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public void validate() {
        System.out.println(this.getClass().getSimpleName() + " Inside validate method");
        if (name != null && value > 0) {
            System.out.println("Validation result for " + this.getClass().getSimpleName() + " is positive");
        } else
            System.out.println("Validation result for " + this.getClass().getSimpleName() + " is negative");
    }
}
