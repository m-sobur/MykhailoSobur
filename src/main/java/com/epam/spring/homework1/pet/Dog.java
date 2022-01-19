package com.epam.spring.homework1.pet;

import com.epam.spring.homework1.interfaces.Animal;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Dog implements Animal {
}
