package com.philippo.algafood.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {MultipleValidator.class})
public @interface Multiple {

    String message() default "invalid multiple";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int number();
}
