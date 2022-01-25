package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.interfaces.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BeanC implements Validator {
    @Value("${beanC.name}")
    private String name;
    @Value("${beanC.value}")
    private int value;

    public BeanC(){
    }
    @Override
    public String toString() {
        return "BeanC{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    private void initMethodC() {
        System.out.println(this.getClass().getSimpleName() + " initMethodC");
    }

    private void destroyMethodC() {
        System.out.println(this.getClass().getSimpleName() + " destroyMethodC");
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
