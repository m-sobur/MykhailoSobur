package com.epam.spring.homework3.quiz.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UAPhoneValidator implements ConstraintValidator<UAPhone, String> {
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return phoneNumber != null
                && phoneNumber.matches("^\\+?3?8?(0\\d{2}\\d{3}\\d{2}\\d{2})$")
                && (phoneNumber.length() > 8)
                && (phoneNumber.length() < 14);
    }

    @Override
    public void initialize(UAPhone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
