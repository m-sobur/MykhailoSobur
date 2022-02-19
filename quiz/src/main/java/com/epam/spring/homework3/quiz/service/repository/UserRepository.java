package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.User;

public interface UserRepository {

    User getUserByEmail(String email);

    User createUser(User user);

    User updateUserByEmail(String email, User user);

    void deleteUserByEmail(String email);


}
