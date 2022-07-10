package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.interfaces.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BeanD implements Validator {

    @Value("${beanD.name}")
    private String name;

    @Value("${beanD.value}")
    private int value;

    public BeanD() {
    }

    @Override
    public String toString() {
        return "BeanD{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    private void initMethodD() {
        System.out.println(this.getClass().getSimpleName() + " initMethodD");
    }

    private void destroyMethodD() {
        System.out.println(this.getClass().getSimpleName() + " destroyMethodD");
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
