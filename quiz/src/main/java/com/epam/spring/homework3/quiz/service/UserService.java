package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.service.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserService {

    User getUserByEmail(String email);

    User createUser(UserDto userDto);

    User updateUserByEmail(String email, UserDto userDto);

    void deleteUserByEmail(String email);

    List<String> getAllUserFirstNameAndLastName();

    Slice<User> getAllUser(Pageable pageable);

}
