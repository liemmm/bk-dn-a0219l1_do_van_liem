package com.lmao.areas.users.customValidations.password;

import com.lmao.areas.users.models.bindingModels.UserBindingModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArePasswordsMatchingValidator implements ConstraintValidator<ArePasswordsMatching, UserBindingModel> {

    @Override
    public void initialize(ArePasswordsMatching arePasswordsMatching) {
    }

    @Override
    public boolean isValid(UserBindingModel userModel, ConstraintValidatorContext constraintValidatorContext) {
        return userModel.getPassword().equals(userModel.getConfirmPassword());
    }
}
