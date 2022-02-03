package com.movies.validations.Validators;


import com.movies.repositories.UserRepository;
import com.movies.validations.annotations.UniqueUserName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Chahir Chalouati
 */
public class UserNameValidator implements ConstraintValidator<UniqueUserName, Object> {

    private final UserRepository userRepository;
    public boolean isUpdate;

    public UserNameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueUserName constraintAnnotation) {
        this.isUpdate = constraintAnnotation.isUpdate();
    }

    @Override
    public boolean isValid(Object username, ConstraintValidatorContext constraintValidatorContext) {
        return isUpdate ? this.userRepository.findByUserName(String.valueOf(username)).isPresent() : this.userRepository.findByUserName(String.valueOf(username)).isEmpty();
    }
}
