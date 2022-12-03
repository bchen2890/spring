package com.bchen.tutorial.spring.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordRegexValidator implements ConstraintValidator<PasswordRegex, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$")) {
            return true;
        }
        return false;
    }

}