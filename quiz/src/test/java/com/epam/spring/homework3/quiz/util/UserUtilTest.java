package com.epam.spring.homework3.quiz.util;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.service.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUtilTest {
    public static final Long ID = 1L;
    public static final String FIRST_NAME = "Mykhailo";
    public static final String LAST_NAME = "Sobur";
    public static final String EMAIL = "msobur123@gmail.com";
    public static final String PASSWORD = "123456";
    public static final Integer USER_ROLE = 1;
    public static final String PHONE_NUMBER = "+380962538046";

    public static User createUser() {
        return User.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .passwd(PASSWORD)
                .userRole(USER_ROLE)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }

    public static UserDto createUserDto() {
        return UserDto.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .passwd(PASSWORD)
                .repeatPasswd(PASSWORD)
                .userRole(USER_ROLE)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }

}
