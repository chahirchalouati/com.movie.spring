package com.movies.services;

import com.movies.domain.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
/**
 * @author Chahir Chalouati
 */
public interface StorageService {

    Path getPath(String path);

    File store(MultipartFile file);

    Resource download(Path path);

    void createStore();


    String generateFileName(String extension);
}
