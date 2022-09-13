package com.epam.spring.homework3.quiz.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UAPhoneValidator.class)
public @interface UAPhone {
    String message() default "Phone number doesn't match with template";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};

}
