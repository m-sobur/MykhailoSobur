package com.epam.spring.homework3.quiz.validation;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RepeatPasswordEqualToPasswordValidator implements ConstraintValidator<RepeatPasswordEqualToPassword, UserDto> {
    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getRepeatPasswd().equals(userDto.getPasswd());
    }

    @Override
    public void initialize(RepeatPasswordEqualToPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
