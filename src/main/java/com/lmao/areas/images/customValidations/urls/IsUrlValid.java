package com.lmao.areas.images.customValidations.urls;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = IsUrlValidValidator.class)
public @interface IsUrlValid {

    String message() default "The URL is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
