package com.movies.services.Impl;

import com.movies.domain.File;
import com.movies.exceptions.FileStoreException;
import com.movies.repositories.FileRepository;
import com.movies.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.UUID;
/**
 * @author Chahir Chalouati
 */
@Service
@Profile("dev")
@Slf4j
public class MockStorageServiceImpl implements StorageService {

    @Value("${file.storage.dir}")
    private String dir;
    private final FileRepository fileRepository;
    private final ResourceLoader resourceLoader;

    public MockStorageServiceImpl(FileRepository fileRepository, ResourceLoader resourceLoader) {
        this.fileRepository = fileRepository;
        this.resourceLoader = resourceLoader;
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
        return UUID.randomUUID().toString().concat(".").concat(extension);
    }

    @Override
    public Path getPath(String path) {
        return Paths.get(path).normalize().toAbsolutePath();
    }

    @Override
    public File store(MultipartFile file) {
        try {
            String[] strings = file.getOriginalFilename().split("\\.");
            final String fileName = this.generateFileName(strings[strings.length - 1]);
            final InputStream inputStream = file.getInputStream();
            final Path path = this.getPath(dir.concat("/").concat(fileName));
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            final File fileToStore = new File()
                    .setName(fileName)
                    .setPath(path.toString())
                    .setDownloadUrl("/files/".concat(fileName))
                    .setType(resolveFileType(file));
            return this.fileRepository.save(fileToStore);

        } catch (IOException e) {
            String format = String.format("unable to store file fileName : %s", file.getName());
            log.info(format);
            throw new FileStoreException(format);
        }
    }

    private File.FileType resolveFileType(MultipartFile file) {
        if (file.getContentType().trim().toLowerCase(Locale.ROOT).startsWith("image")) return File.FileType.IMAGE;
        if (file.getContentType().trim().toLowerCase(Locale.ROOT).startsWith("video")) return File.FileType.VIDEO;
        return File.FileType.OTHER;
    }

    @Override
    public Resource download(Path path) {
        try {
            return resourceLoader.getResource(path.toString());
        } catch (Exception exception) {
            String format = String.format("unable to store file fileName : %s", path.getFileName());
            log.info(format);
            throw new FileStoreException(format);
        }
    }


}
