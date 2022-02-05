package com.movies.services;

import com.movies.domain.File;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    ResponseEntity<?> getFile(String filename, HttpServletRequest request);
}
