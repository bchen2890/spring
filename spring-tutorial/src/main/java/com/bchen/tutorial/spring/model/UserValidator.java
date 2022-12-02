package com.bchen.tutorial.spring.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmpty(errors, "age", "NotEmpty.user.age");
        if(user.getAge() == null || user.getAge()<18){
            errors.rejectValue("age", "Min.user.age");
        }
    }
}
