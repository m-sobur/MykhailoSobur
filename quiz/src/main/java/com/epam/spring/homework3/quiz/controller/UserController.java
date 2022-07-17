package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.controller.mapper.UserMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/get/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        try {
            ResponseEntity<UserDto> result = ResponseEntity.status(HttpStatus.OK).body(userMapper.userToUserDto(userService.getUserByEmail(email)));
            LOGGER.info("CONTROLLER LAYER: getUserByEmail method ");
            return result;
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        try {
            ResponseEntity<UserDto> result = ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userToUserDto(userService.createUser(userDto)));
            LOGGER.info("CONTROLLER LAYER: createUser method ");
            return result;
        } catch (ElementAlreadyExistException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/update/{email}")
    public ResponseEntity<UserDto> updateUserByEmail(@PathVariable String email, @RequestBody UserDto userDto) {
        try {
            ResponseEntity<UserDto> result = ResponseEntity.status(HttpStatus.OK).body(userMapper.userToUserDto(userService.updateUserByEmail(email, userDto)));
            LOGGER.info("CONTROLLER LAYER: updateUserByEmail method ");
            return result;
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(value = "/delete/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
        try {
            userService.deleteUserByEmail(email);
            LOGGER.info("CONTROLLER LAYER: deleteUserByEmail method ");
            return ResponseEntity.status(HttpStatus.OK).body("User with email '" + email + "' deleted successfully");
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
