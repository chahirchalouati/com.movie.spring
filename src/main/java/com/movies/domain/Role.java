package com.movies.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
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
    @Indexed
    private String role;
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private LocalDateTime createdAt;

    public Role setRole(String role) {
        this.role = role;
        return this;
    }
}
