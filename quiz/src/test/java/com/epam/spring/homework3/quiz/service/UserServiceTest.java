package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.controller.mapper.UserMapper;
import com.epam.spring.homework3.quiz.exception.repository.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.impl.UserServiceImpl;
import com.epam.spring.homework3.quiz.service.model.User;
import com.epam.spring.homework3.quiz.service.repository.UserRepository;
import com.epam.spring.homework3.quiz.util.UserUtilTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Spy
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

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
                        user2
                );

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
    void getAllUserFirstNameAndLastNameTest_NoSuchElementException() {
        when(userRepository.getAllUserFirstNameAndLastName()).thenReturn(Optional.empty());

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> userService.getAllUserFirstNameAndLastName())
                .withMessage("No users in the database");

        verify(userRepository, times(1)).getAllUserFirstNameAndLastName();
    }

    @Test
    void getAllUserTest() {
        User user = UserUtilTest.createUser();

        User user2 = UserUtilTest.createUser();
        user2.setId(2L);

        User user3 = UserUtilTest.createUser();
        user3.setId(3L);

        List<User> userList = Arrays.asList(
                user,
                user2,
                user3
        );

        Pageable pageable = Pageable.unpaged();

        when(userRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(userList, pageable, userList.size()));

        Slice<User> resultSlice = userService.getAllUser(pageable);

        assertThat(resultSlice.getContent(), hasItems(user, user2, user3));

        verify(userRepository, times(1)).findAll(pageable);
    }

    @Test
    void getAllUserTest_NoSuchElementException() {
        List<User> userList = new ArrayList<>();

        when(userRepository.findAll(Pageable.unpaged()))
                .thenReturn(new PageImpl<>(userList, Pageable.unpaged(), 0));

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> userService.getAllUser(Pageable.unpaged()))
                .withMessage("User not found in the 'PostgresDB' while executing getAllUser ");

        verify(userRepository, times(1)).findAll(Pageable.unpaged());
    }

    @Test
    void shouldDeleteUser_whenGivenEmailFound() {
        User user = UserUtilTest.createUser();

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        userService.deleteUserByEmail(user.getEmail());

        verify(userRepository, times(1)).deleteUserByEmail(user.getEmail());
    }

    @Test
    void shouldThrow_NoSuchElementException_when_deleteUserByEmail() {
        User user = UserUtilTest.createUser();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> userService.deleteUserByEmail(user.getEmail()))
                .withMessage("User not found in the 'PostgresDB' while executing deleteUserByEmail " + user.getEmail());
    }

    @Test
    void shouldCreateUserTest_when_GivenNewUser() {
        UserDto userDto = UserUtilTest.createUserDto();

        User user = userMapper.userDtoToUser(userDto);

        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(userDto);

        assertThat(createdUser, equalTo(user));

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldThrow_ElementAlreadyExistException_ifUserFound_when_CreateUserTest() {
        UserDto userDto = UserUtilTest.createUserDto();

        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        assertThatExceptionOfType(ElementAlreadyExistException.class)
                .isThrownBy(() -> userService.createUser(userDto))
                .withMessage("User is already exist at 'PostgresDB' while executing createUser " + userDto.getEmail());
    }

    @Test
    void shouldUpdateUserByEmail_when_GivenUserDtoToUpdate() {
        UserDto userDto = UserUtilTest.createUserDto();
        userDto.setFirstName("test");
        userDto.setLastName("test");

        User userToUpdate = userMapper.userDtoToUser(userDto);

        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(userToUpdate));

        User updateUserByEmail = userService.updateUserByEmail(userToUpdate.getEmail(), userDto);

        assertThat(updateUserByEmail, equalTo(userToUpdate));

        verify(userRepository, times(1)).findByEmail(userDto.getEmail());
    }

    @Test
    void shouldThrow_NoSuchElementException_when_GivenNonexistentEmail() {
        UserDto userDto = UserUtilTest.createUserDto();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> userService.updateUserByEmail(anyString(), userDto))
                .withMessage("User not found in the 'PostgresDB' while executing updateUserByEmail");

        verify(userRepository, times(0)).findByEmail(userDto.getEmail());
    }

}
