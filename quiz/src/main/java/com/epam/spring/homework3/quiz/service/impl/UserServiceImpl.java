package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.controller.mapper.UserMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.UserService;
import com.epam.spring.homework3.quiz.service.model.User;
import com.epam.spring.homework3.quiz.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getUserByEmail(String email) throws NoSuchElementException {
        User user = userRepository.getUserByEmail(email);
        log.info("SERVICE LAYER: getUserByEmail method "+ email);
        return user;
    }

    @Override
    public User createUser(UserDto userDto) throws ElementAlreadyExistException {
        User user = userMapper.userDtoToUser(userDto);
        userRepository.createUser(user);
        log.info("SERVICE LAYER: createUser method "+ user);
        return user;
    }

    @Override
    public User updateUserByEmail(String email, UserDto userDto) throws NoSuchElementException{
        User user = userMapper.userDtoToUser(userDto);
        user = userRepository.updateUserByEmail(email, user);
        log.info("SERVICE LAYER: updateUserByEmail method " + user);
        return user;
    }

    @Override
    public void deleteUserByEmail(String email) throws NoSuchElementException {
        userRepository.deleteUserByEmail(email);
        log.info("SERVICE LAYER: deleteUserByEmail " + email);
    }
}
