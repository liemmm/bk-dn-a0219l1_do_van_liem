package com.lmao.areas.users.customValidations.username;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = IsUsernameExistentValidator.class)
public @interface IsUsernameExistent {

    String message() default "This username already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
