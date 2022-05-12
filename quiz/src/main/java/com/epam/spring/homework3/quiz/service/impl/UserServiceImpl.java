package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.controller.mapper.UserMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.NoSuchUserException;
import com.epam.spring.homework3.quiz.exception.repositoryException.UserAlreadyExistsException;
import com.epam.spring.homework3.quiz.service.UserService;
import com.epam.spring.homework3.quiz.service.model.User;
import com.epam.spring.homework3.quiz.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserByEmail(String email) throws NoSuchUserException {
        User user = userRepository.getUserByEmail(email);
        log.info("SERVICE LAYER: getUserByEmail method "+ email);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) throws UserAlreadyExistsException {
        User user = userMapper.userDtoToUser(userDto);
        user.setId_usr(UUID.randomUUID());
        user = userRepository.createUser(user);
        log.info("SERVICE LAYER: createUser method "+ user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUserByEmail(String email, UserDto userDto) throws NoSuchUserException{
        User user = userMapper.userDtoToUser(userDto);
        user = userRepository.updateUserByEmail(email, user);
        log.info("SERVICE LAYER: updateUserByEmail method " + user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public void deleteUserByEmail(String email) throws NoSuchUserException {
        userRepository.deleteUserByEmail(email);
        log.info("SERVICE LAYER: deleteUserByEmail " + email);
    }
}
