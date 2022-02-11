package com.movies.domain;

import lombok.*;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = Genre.COLLECTION_NAME)
@Setter(AccessLevel.NONE)
public class Genre {
    public static final String COLLECTION_NAME = "genres";
    private String id;
//    @Indexed(unique = true)
    private String name;

    public Genre setId(String id) {
        this.id = id;
        return this;
    }

    public Genre setName(String name) {
        this.name = name;
        return this;
    }
}
