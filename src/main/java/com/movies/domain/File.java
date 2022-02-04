package com.movies.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = File.COLLECTION_NAME)
@Setter(AccessLevel.NONE)
public class File {
    public static final String COLLECTION_NAME = "files";
    public enum FileType {VIDEO, OTHER, IMAGE}
    private String id;
    private String name;
    private String path;
    private String downloadUrl;
    private FileType type;

    public File setId(String id) {
        this.id = id;
        return this;
    }

    public File setName(String name) {
        this.name = name;
        return this;
    }

    public File setPath(String path) {
        this.path = path;
        return this;
    }

    public File setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        return this;
    }

    public File setType(FileType type) {
        this.type = type;
        return this;
    }
}
