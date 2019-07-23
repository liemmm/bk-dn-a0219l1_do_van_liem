package com.lmao.areas.users.customValidations.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ArePasswordsMatchingValidator.class)
public @interface ArePasswordsMatching {

    String message() default "Passwords Not Matching";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
