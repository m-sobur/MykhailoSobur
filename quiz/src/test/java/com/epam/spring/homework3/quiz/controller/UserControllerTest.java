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

import static com.epam.spring.homework3.quiz.util.UserUtilTest.EMAIL;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
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

    }

}
