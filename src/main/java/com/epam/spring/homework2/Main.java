package com.epam.spring.homework2;

import com.epam.spring.homework2.config.BeansConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeansConfig.class);

        System.out.println("--------------------");

        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        System.out.println("--------------------");

        for (String bean : applicationContext.getBeanDefinitionNames()) {
            System.out.println(applicationContext.getBeanDefinition(bean));
        }

        System.out.println("--------------------");

        applicationContext.close();
    }
}
