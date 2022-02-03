package com.movies.services.Impl;

import com.movies.domain.File;
import com.movies.repositories.FileRepository;
import com.movies.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Profile("prod")
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Value("${file.storage.dir : ./defaultStore}")
    private String dir;
    private final FileRepository fileRepository;

    public StorageServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Path getPath(String path) {
        return Paths.get(path).normalize().toAbsolutePath();
    }

    @Override
    public File store(MultipartFile file) {
        return null;
    }

    @Override
    public Resource download(Path path) {
        return null;
    }

    @PostConstruct
    @Override
    public void createStore() {
        final Path absolutePath = this.getPath(dir);
        try {
            Files.createDirectories(absolutePath);
        } catch (Exception exception) {
            log.info(String.valueOf(NestedExceptionUtils.getMostSpecificCause(exception)));
        }
    }

    @Override
    public String generateFileName(String extension) {
        return UUID.randomUUID().toString();
    }
}
