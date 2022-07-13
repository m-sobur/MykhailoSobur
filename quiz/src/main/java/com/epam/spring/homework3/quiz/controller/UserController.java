package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.dto.group.OnUpdate;
import com.epam.spring.homework3.quiz.controller.mapper.UserMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = "UserController description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @ApiOperation("Get user by email")
    @GetMapping(value = "/get/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        try {
            ResponseEntity<UserDto> result = ResponseEntity.status(HttpStatus.OK).body(userMapper.userToUserDto(userService.getUserByEmail(email)));
            log.info("CONTROLLER LAYER: getUserByEmail method ");
            return result;
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation("Create user")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
        try {
            ResponseEntity<UserDto> result = ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userToUserDto(userService.createUser(userDto)));
            log.info("CONTROLLER LAYER: createUser method ");
            return result;
        } catch (ElementAlreadyExistException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Update user by email")
    @PutMapping(value = "/update/{email}")
    public ResponseEntity<UserDto> updateUserByEmail(@PathVariable String email, @RequestBody @Validated(OnUpdate.class) UserDto userDto) {
        try {
            ResponseEntity<UserDto> result = ResponseEntity.status(HttpStatus.OK).body(userMapper.userToUserDto(userService.updateUserByEmail(email, userDto)));
            log.info("CONTROLLER LAYER: updateUserByEmail method ");
            return result;
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation("Delete user by email")
    @DeleteMapping(value = "/delete/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
        try {
            userService.deleteUserByEmail(email);
            log.info("CONTROLLER LAYER: deleteUserByEmail method ");
            return ResponseEntity.status(HttpStatus.OK).body("User with email '" + email + "' deleted successfully");
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}