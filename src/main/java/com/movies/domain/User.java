package com.movies.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = User.COLLECTION_NAME)
@Setter(AccessLevel.NONE)
@Builder
public class User {
    public static final String COLLECTION_NAME = "users";
    @NotBlank(message = "firstName can't be blank")
    public String firstName;
    @NotBlank(message = "lastName can't be blank")
    public String lastName;
    @Id
    private String id;
    @NotBlank(message = "userName can't be blank")
    @Indexed
    private String userName;
    @NotBlank(message = "password can't be blank")
    @JsonIgnore
    private String password;
    private Set<Role> roles = new HashSet<>();

    @Indexed
    private String avatar;
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private LocalDateTime createdAt;


    private Profile profile;

    public String getFullName() {
        return String.format("%s %s", this.getFirstName(), this.getLastName());
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
