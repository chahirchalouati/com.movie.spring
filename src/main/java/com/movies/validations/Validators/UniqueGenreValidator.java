package com.movies.validations.Validators;

import com.movies.repositories.GenreRepository;
import com.movies.validations.annotations.UniqueGenre;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Chahir Chalouati
 */
public class UniqueGenreValidator implements ConstraintValidator<UniqueGenre, String> {

    @Autowired
    private  GenreRepository genreRepository;

    @Override
    public void initialize(UniqueGenre constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return !this.genreRepository.existsByName(name);
    }
}
