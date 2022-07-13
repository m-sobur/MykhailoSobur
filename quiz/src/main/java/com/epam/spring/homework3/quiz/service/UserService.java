package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.service.model.User;

public interface UserService {

    User getUserByEmail(String email);

    User createUser(UserDto userDto);

    User updateUserByEmail(String email, UserDto userDto);

    void deleteUserByEmail(String email);
}