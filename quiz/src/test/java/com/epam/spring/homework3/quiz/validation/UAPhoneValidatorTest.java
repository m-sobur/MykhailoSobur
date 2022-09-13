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
class UAPhoneValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @InjectMocks
    private UAPhoneValidator uaPhoneValidator;

    @Test
    void givenWrongPhoneNumber_whenIsValid_thenReturnFalse() {
        UserDto userDto = UserUtilTest.createUserDto();
        userDto.setPhoneNumber("test");

        boolean isValidResult = uaPhoneValidator.isValid(userDto.getPhoneNumber(), constraintValidatorContext);

        assertFalse(isValidResult);
    }

    @Test
    void givenValidPhoneNumber_whenIsValid_thenReturnTrue() {
        UserDto userDto = UserUtilTest.createUserDto();

        boolean isValidResult = uaPhoneValidator.isValid(userDto.getPhoneNumber(), constraintValidatorContext);

        assertTrue(isValidResult);
    }

}
