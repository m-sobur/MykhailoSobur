package com.epam.spring.homework2;

import com.epam.spring.homework2.config.BeansConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeansConfig.class);
        String[] beanName = applicationContext.getBeanDefinitionNames();

        System.out.println("--------------------");

        for (String bean : beanName) {
            System.out.println(applicationContext.getBean(bean));
        }

        System.out.println("--------------------");

        applicationContext.close();
    }
}
