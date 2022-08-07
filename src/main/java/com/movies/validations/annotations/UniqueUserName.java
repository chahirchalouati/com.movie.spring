package com.movies.validations.annotations;

import com.movies.validations.Validators.UserNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Chahir Chalouati
 */
@Documented
@Constraint(validatedBy = UserNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserName {
    String message() default "username already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean isUpdate() default false;
}
