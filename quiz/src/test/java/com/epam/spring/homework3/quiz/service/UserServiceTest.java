package com.epam.spring.homework3.quiz.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.spring.homework3.quiz.controller.mapper.UserMapper;
import com.epam.spring.homework3.quiz.service.impl.UserServiceImpl;
import com.epam.spring.homework3.quiz.service.model.User;
import com.epam.spring.homework3.quiz.service.repository.UserRepository;
import com.epam.spring.homework3.quiz.util.UserUtilTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Spy
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void getUserByEmailTest() {
        User user = UserUtilTest.createUser();

        when(userRepository.findByEmail(UserUtilTest.EMAIL)).thenReturn(Optional.of(user));

        User userFromUserService = userService.getUserByEmail(UserUtilTest.EMAIL);

        assertThat(
                userFromUserService,
                allOf(
                        hasProperty("id", equalTo(UserUtilTest.ID)),
                        hasProperty("firstName", equalTo(UserUtilTest.FIRST_NAME)),
                        hasProperty("lastName", equalTo(UserUtilTest.LAST_NAME)),
                        hasProperty("email", equalTo(UserUtilTest.EMAIL)),
                        hasProperty("passwd", equalTo(UserUtilTest.PASSWORD)),
                        hasProperty("userRole", equalTo(UserUtilTest.USER_ROLE)),
                        hasProperty("phoneNumber", equalTo(UserUtilTest.PHONE_NUMBER))
                )
        );

        verify(userRepository, times(1)).findByEmail(UserUtilTest.EMAIL);

    }

    @Test
    void getUserByEmailTest_NoSuchElementException() {
        when(userRepository.findByEmail(UserUtilTest.EMAIL)).thenReturn(Optional.empty());

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> userService.getUserByEmail(UserUtilTest.EMAIL))
                .withMessage("User not found in the 'PostgresDB' while executing getUserByEmail " + UserUtilTest.EMAIL);

        verify(userRepository, times(1)).findByEmail(UserUtilTest.EMAIL);
    }

    @Test
    void getAllUserFirstNameAndLastNameTest() {
        User user = UserUtilTest.createUser();
        User user2 = UserUtilTest.createUser();
        user2.setId(2L);
        user2.setFirstName("Taras");
        user2.setLastName("Boychuk");

        List<User> userList =
                Arrays.asList(
                        user,
                        user2);

        List<String> userFirstNameLastNameList = userList.stream()
                .map(u -> u.getFirstName() + " " + u.getLastName())
                .collect(Collectors.toList());

        when(userRepository.getAllUserFirstNameAndLastName()).thenReturn(Optional.of(userFirstNameLastNameList));

        List<String> userFirstNameLastNameListFromService = userService.getAllUserFirstNameAndLastName();

        assertThat(userFirstNameLastNameListFromService.get(0), equalTo(user.getFirstName() + " " + user.getLastName()));
        assertThat(userFirstNameLastNameListFromService.get(1), equalTo(user2.getFirstName() + " " + user2.getLastName()));

        verify(userRepository, times(1)).getAllUserFirstNameAndLastName();
    }

    @Test
    void getAllUserFirstNameAndLastNameTest_NoSuchElementException(){
        when(userRepository.getAllUserFirstNameAndLastName()).thenReturn(Optional.empty());

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> userService.getAllUserFirstNameAndLastName())
                .withMessage("No users in the database");

        verify(userRepository, times(1)).getAllUserFirstNameAndLastName();
    }


}
