package com.epam.spring.homework3.quiz.validation;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.util.UserUtilTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class RepeatPasswordEqualToPasswordValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @InjectMocks
    private RepeatPasswordEqualToPasswordValidator repeatPasswordEqualToPasswordValidator;

    @Test
    void givenEqualPassword_whenIsValid_thenReturnTrue() {
        UserDto userDto = UserUtilTest.createUserDto();

        boolean isValidResult = repeatPasswordEqualToPasswordValidator.isValid(userDto, constraintValidatorContext);

        assertTrue(isValidResult);
    }

    @Test
    void givenDifferentPassword_whenIsValid_thenReturnFalse() {
        UserDto userDto = UserUtilTest.createUserDto();
        userDto.setRepeatPasswd("test");

        boolean isValidResult = repeatPasswordEqualToPasswordValidator.isValid(userDto, constraintValidatorContext);

        assertFalse(isValidResult);
    }
}
