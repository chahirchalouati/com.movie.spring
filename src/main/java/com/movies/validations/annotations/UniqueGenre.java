package com.movies.validations.annotations;

import com.movies.validations.Validators.UniqueGenreValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Chahir Chalouati
 */
@Documented
@Constraint(validatedBy = UniqueGenreValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueGenre {

    String message() default "genre should be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
