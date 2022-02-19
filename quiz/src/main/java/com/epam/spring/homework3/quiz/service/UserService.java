package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;

public interface UserService {

    UserDto getUserByEmail(String email);

    UserDto createUser(UserDto userDto);

    UserDto updateUserByEmail(String email, UserDto userDto);

    void deleteUserByEmail(String email);

}
