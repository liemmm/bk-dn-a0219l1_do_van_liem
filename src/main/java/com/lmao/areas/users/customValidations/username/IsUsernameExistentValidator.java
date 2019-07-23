package com.lmao.areas.users.customValidations.username;

import com.lmao.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsUsernameExistentValidator implements ConstraintValidator<IsUsernameExistent, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(IsUsernameExistent isUsernameExistent) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        boolean isExistent = this.userService.isUsernameExistent(username);
        return !isExistent;
    }
}
