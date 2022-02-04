package com.movies.services.Impl;

import com.movies.domain.File;
import com.movies.exceptions.FileStoreException;
import com.movies.repositories.FileRepository;
import com.movies.services.StorageService;
import com.movies.utils.FileUtils;
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
import java.util.Objects;
import java.util.UUID;

import static com.movies.utils.FileUtils.BACK_SLASH;
import static com.movies.utils.FileUtils.POINT;

@Service
@Profile("prod")
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Value("${file.storage.dir}")
    private String dir;
    private final FileRepository fileRepository;
    private final ResourceLoader resourceLoader;

    public StorageServiceImpl(FileRepository fileRepository, ResourceLoader resourceLoader) {
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
        return UUID.randomUUID().toString().concat(POINT).concat(extension);
    }

    @Override
    public Path getPath(String path) {
        return Paths.get(path).normalize().toAbsolutePath();
    }

    @Override
    public File store(MultipartFile file) {
        try {
            String[] splitResult = Objects.requireNonNull(file.getOriginalFilename()).split(FileUtils.EXTENSION_SPLITTER);
            final String fileName = this.generateFileName(splitResult[splitResult.length - 1]);
            final InputStream inputStream = file.getInputStream();

            final Path path = this.getPath(dir.concat(BACK_SLASH).concat(fileName));
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
        if (this.standardize(file).startsWith("image")) return File.FileType.IMAGE;
        if (this.standardize(file).startsWith("video")) return File.FileType.VIDEO;
        return File.FileType.OTHER;
    }

    private String standardize(MultipartFile file) {
        return file.getContentType().trim().toLowerCase(Locale.ROOT);
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
