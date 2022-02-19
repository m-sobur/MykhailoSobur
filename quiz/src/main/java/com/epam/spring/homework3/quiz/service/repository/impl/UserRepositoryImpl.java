package com.epam.spring.homework3.quiz.service.repository.impl;

import com.epam.spring.homework3.quiz.service.model.User;
import com.epam.spring.homework3.quiz.service.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final List<User> temporaryDataBase = new ArrayList<>();


    @Override
    public User getUserByEmail(String email) {
        return temporaryDataBase.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("User not found!"));
    }

    @Override
    public User createUser(User user) {
        temporaryDataBase.add(user);
        return user;
    }

    @Override
    public User updateUserByEmail(String email, User user) {
        boolean isDeleted = temporaryDataBase.removeIf(user1 -> user1.getEmail().equals(email));
        if (isDeleted){
            temporaryDataBase.add(user);
        }else{
            throw new RuntimeException("User not found");
        }
        return user;
    }

    @Override
    public void deleteUserByEmail(String email) {
        temporaryDataBase.removeIf(user -> user.getEmail().equals(email));
    }
}
