package com.epam.spring.homework3.quiz.controller;


import com.epam.spring.homework3.quiz.config.TestConfig;
import com.epam.spring.homework3.quiz.controller.assembler.UserAssembler;
import com.epam.spring.homework3.quiz.controller.assembler.model.UserModel;
import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.controller.mapper.UserMapper;
import com.epam.spring.homework3.quiz.controller.mapper.UserMapperImpl;
import com.epam.spring.homework3.quiz.service.UserService;
import com.epam.spring.homework3.quiz.service.model.User;
import com.epam.spring.homework3.quiz.util.UserUtilTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.epam.spring.homework3.quiz.util.UserUtilTest.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest({UserController.class, ErrorHandler.class})
@ContextConfiguration(classes = UserMapperImpl.class)
@AutoConfigureMockMvc
@Import({TestConfig.class, UserController.class})
class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserAssembler userAssembler;

    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getUserByEmailTest() throws Exception {
        User user = UserUtilTest.createUser();

        when(userService.getUserByEmail(EMAIL)).thenReturn(user);

        UserDto userDto = userMapper.userToUserDto(user);

        UserModel userModel = new UserModel(userDto);

        when(userAssembler.toModel(userDto)).thenReturn(userModel);

        mockMvc.perform(get("/user/" + EMAIL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..email").value(EMAIL));

        verify(userService, times(1)).getUserByEmail(EMAIL);
    }

    @Test
    void createUserTest() throws Exception {
        User user = UserUtilTest.createUser();
        UserDto userDtoInput = UserUtilTest.createUserDto();
        userDtoInput.setId(null);

        when(userService.createUser(userDtoInput)).thenReturn(user);

        UserDto userDto = userMapper.userToUserDto(user);

        UserModel userModel = new UserModel(userDto);

        when(userAssembler.toModel(userDto)).thenReturn(userModel);

        mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDtoInput))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(userModel)));

        verify(userService, times(1)).createUser(userDtoInput);
    }

    @Test
    void createUserWithUnCorrectPasswordAndPhoneNumberTest() throws Exception {
        User user = UserUtilTest.createUser();
        user.setPasswd("randomNPass");
        user.setPhoneNumber("randomNumber");

        UserDto userDtoInput = UserUtilTest.createUserDto();
        userDtoInput.setId(null);
        userDtoInput.setPasswd("randomNPass");
        userDtoInput.setPhoneNumber("randomNumber");

        when(userService.createUser(userDtoInput)).thenReturn(user);

        UserDto userDto = userMapper.userToUserDto(user);

        UserModel userModel = new UserModel(userDto);

        when(userAssembler.toModel(userDto)).thenReturn(userModel);

        mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDtoInput))
                )
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(userService, times(0)).createUser(userDtoInput);
    }

    @Test
    void updateUserByEmailTest() throws Exception {
        User user = UserUtilTest.createUser();
        user.setLastName("TestLastName");
        user.setFirstName("TestFirstName");

        UserDto userDtoInput = UserUtilTest.createUserDto();
        userDtoInput.setEmail(null);
        userDtoInput.setUserRole(null);
        userDtoInput.setRepeatPasswd(null);
        userDtoInput.setPasswd(null);
        userDtoInput.setId(null);

        userDtoInput.setLastName("TestLastName");
        userDtoInput.setFirstName("TestFirstName");

        when(userService.updateUserByEmail(EMAIL, userDtoInput)).thenReturn(user);

        UserDto userDto = userMapper.userToUserDto(user);

        UserModel userModel = new UserModel(userDto);
        when(userAssembler.toModel(userDto)).thenReturn(userModel);

        mockMvc.perform(
                        put("/user/" + EMAIL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDtoInput))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(userModel)));

        verify(userService, times(1)).updateUserByEmail(EMAIL, userDtoInput);
    }

    @Test
    void deleteUserByEmailTest() throws Exception {
        doNothing().when(userService).deleteUserByEmail(EMAIL);

        mockMvc
                .perform(delete("/user/" + EMAIL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("User with email '" + EMAIL + "' deleted successfully"));

        verify(userService, times(1)).deleteUserByEmail(EMAIL);
    }

    @Test
    void getAllUserFirstNameAndLastNameTest() throws Exception {
        List<String> result = new ArrayList<>();
        result.add(FIRST_NAME + " " + LAST_NAME);

        when(userService.getAllUserFirstNameAndLastName()).thenReturn(result);

        mockMvc
                .perform(get("/user/getAllUserFirstNameAndLastName"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(result)));

        verify(userService, times(1)).getAllUserFirstNameAndLastName();
    }
}
