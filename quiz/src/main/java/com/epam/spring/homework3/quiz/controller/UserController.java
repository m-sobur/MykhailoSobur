package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.assembler.UserAssembler;
import com.epam.spring.homework3.quiz.controller.assembler.model.UserModel;
import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.dto.group.OnUpdate;
import com.epam.spring.homework3.quiz.controller.mapper.UserMapper;
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
    private final UserAssembler userAssembler;

    @ApiOperation("Get user by email")
    @GetMapping(value = "/get/{email}")
    public UserModel getUserByEmail(@PathVariable String email) {
        log.info("CONTROLLER LAYER: getUserByEmail method entry");
        UserDto result = userMapper.userToUserDto(userService.getUserByEmail(email));
        log.info("CONTROLLER LAYER: getUserByEmail method exit");
        return userAssembler.toModel(result);
    }

    @ApiOperation("Create user")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserModel createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
        log.info("CONTROLLER LAYER: createUser method entry");
        UserDto result = userMapper.userToUserDto(userService.createUser(userDto));
        log.info("CONTROLLER LAYER: createUser method exit");
        return userAssembler.toModel(result);
    }

    @ApiOperation("Update user by email")
    @PutMapping(value = "/update/{email}")
    public UserModel updateUserByEmail(@PathVariable String email, @RequestBody @Validated(OnUpdate.class) UserDto userDto) {
        log.info("CONTROLLER LAYER: updateUserByEmail method entry");
        UserDto result = userMapper.userToUserDto(userService.updateUserByEmail(email, userDto));
        log.info("CONTROLLER LAYER: updateUserByEmail method exit");
        return userAssembler.toModel(result);
    }

    @ApiOperation("Delete user by email")
    @DeleteMapping(value = "/delete/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
        log.info("CONTROLLER LAYER: deleteUserByEmail method entry");
        userService.deleteUserByEmail(email);
        log.info("CONTROLLER LAYER: deleteUserByEmail method exit");
        return ResponseEntity.status(HttpStatus.OK).body("User with email '" + email + "' deleted successfully");
    }
}
