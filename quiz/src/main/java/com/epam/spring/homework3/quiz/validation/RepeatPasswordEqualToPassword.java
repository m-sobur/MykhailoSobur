package com.epam.spring.homework3.quiz.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RepeatPasswordEqualToPasswordValidator.class)
public @interface RepeatPasswordEqualToPassword {
    String message() default "Repeat password doesn't equal password";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
