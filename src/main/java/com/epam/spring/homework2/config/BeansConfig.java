package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import org.springframework.context.annotation.*;

@Configuration
@Import(OtherConfig.class)
@ComponentScan("com.epam.spring.homework2.beans")
@PropertySource("classpath:application.properties")
public class BeansConfig {

    @Bean(initMethod = "initMethodD",
            destroyMethod = "destroyMethodD")
    public BeanD beanD() {
        System.out.println("BeanD @DependsOn");
        return new BeanD();
    }

    @Bean(initMethod = "initMethodB",
            destroyMethod = "destroyMethodB")
    @DependsOn("beanD")
    public BeanB beanB() {
        System.out.println("BeanB @DependsOn BeanD");
        return new BeanB();
    }

    @Bean(initMethod = "initMethodC",
            destroyMethod = "destroyMethodC")
    @DependsOn("beanB")
    public BeanC beanC() {
        System.out.println("BeanC @DependsOn BeanB");
        return new BeanC();
    }
}