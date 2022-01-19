package com.epam.spring.homework1.pet;

import com.epam.spring.homework1.interfaces.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Pet {
    @Autowired
    private List<Animal> pets;

    public void printPets() {
        for (Animal animal : pets) {
            System.out.println(animal.getClass().getSimpleName());
        }
    }
}
