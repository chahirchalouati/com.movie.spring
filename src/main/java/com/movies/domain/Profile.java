package com.movies.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = Profile.COLLECTION_NAME)
public class Profile {

    public static final String COLLECTION_NAME = "profiles";
    private String id;

    @NotBlank(message = "user_id can't be blank")
    @Field(name = "user_id")
    @Indexed(background = true, direction = IndexDirection.ASCENDING)
    private String userId;

    @NotBlank(message = "user_avatar can't be blank")
    @Field(name = "user_avatar")
    private String avatar;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private LocalDateTime createdAt;

    public Profile setId(String id) {
        this.id = id;
        return this;
    }

    public Profile setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Profile setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
}
