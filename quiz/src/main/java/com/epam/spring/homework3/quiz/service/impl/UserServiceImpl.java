package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.controller.mapper.UserMapper;
import com.epam.spring.homework3.quiz.service.UserService;
import com.epam.spring.homework3.quiz.service.model.User;
import com.epam.spring.homework3.quiz.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto getUserByEmail(String email) {
        log.info("getUserByEmail method " + email);
        User user = userRepository.getUserByEmail(email);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser method " + userDto);
        User user = userMapper.userDtoToUser(userDto);
        user = userRepository.createUser(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUserByEmail(String email, UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        user = userRepository.updateUserByEmail(email, user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public void deleteUserByEmail(String email) {
        log.info("deleteUserByEmail"  + email);
        userRepository.deleteUserByEmail(email);
    }

  /*  private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .id_usr(user.getId_usr())
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .email(user.getEmail())
                .usr_role(user.getUsr_role())
                .build();

    }

    private User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .id_usr(userDto.getId_usr())
                .first_name(userDto.getFirst_name())
                .last_name(userDto.getLast_name())
                .email(userDto.getEmail())
                .passwd(userDto.getPasswd())
                .usr_role(userDto.getUsr_role())
                .build();

    }*/
}
