package com.bchen.tutorial.spring.model;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = PasswordRegexValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface PasswordRegex {
    String message() default "Password must contain a letter and a number";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}