package com.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Person {
    @NotBlank(message = "firstName can't be blank")
    public String firstName;
    @NotBlank(message = "lastName can't be blank")
    public String lastName;

    public String getFullName() {
        return String.format("%s %s", this.getFirstName(), this.getLastName());
    }
}
