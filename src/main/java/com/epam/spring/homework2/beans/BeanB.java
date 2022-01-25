package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.interfaces.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BeanB implements Validator {
    @Value("${beanB.name}")
    private String name;
    @Value("${beanB.value}")
    private int value;

    public BeanB(){
    }

    @Override
    public String toString() {
        return "BeanB{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    private void initMethodB() {
        System.out.println(this.getClass().getSimpleName() + " initMethodB");
    }

    private void destroyMethodB() {
        System.out.println(this.getClass().getSimpleName() + " destroyMethodB");
    }

    private void otherInitMethod() {
        System.out.println(BeanB.class.getSimpleName() + ": otherInitMethod");
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
