package com.movies.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = Role.COLLECTION_NAME)
public class Role {
    public static final String COLLECTION_NAME = "roles";
    @Id
    private String id;
    @Indexed(background = true, direction = IndexDirection.ASCENDING, name = "role_1")
    private String role;

    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;

    public Role setRole(String role) {
        this.role = role;
        return this;
    }
}
