package com.movies.services.Impl;

import com.movies.domain.File;
import com.movies.exceptions.EntityNotFoundException;
import com.movies.exceptions.FileStoreException;
import com.movies.repositories.FileRepository;
import com.movies.services.StorageService;
import com.movies.utils.FileStorageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static com.movies.utils.FileUtils.SLASH;

/**
 * @author Chahir Chalouati
 */
@Service
@Profile("dev")
@Slf4j
public class MockStorageServiceImpl implements StorageService {

    @Value("${file.storage.dir}")
    private String dir;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private FileStorageUtils fileStorageUtils;

    @PostConstruct
    @Override
    public void createStore() {
        Arrays.stream(File.FileType.values())
                .map(Enum::toString)
                .map(String::toLowerCase)
                .map(s -> s.concat("s"))
                .forEach(createDirs());
    }

    @Override
    public String generateFileName(String extension) {
        return UUID.randomUUID().toString().concat(".").concat(extension);
    }

    @Override
    public ResponseEntity<?> getFile(String filename, HttpServletRequest request) {
        final File file = fileRepository.findByName(filename).orElseThrow(() -> new EntityNotFoundException("not found"));
        final Path path = fileStorageUtils.getPath(file.getPath());
        final Resource resource = fileStorageUtils.getResource(path);
        return Objects.nonNull(resource) && resource.exists() ? this.downLoadFile(request, resource) : ResponseEntity.notFound().build();

    }

    public ResponseEntity<Resource> downLoadFile(HttpServletRequest request, Resource resource) {
        final String contentType = fileStorageUtils.getContentType(request, resource);
        final String contentLength = fileStorageUtils.getContentLength(resource);
        final CacheControl cacheControl = CacheControl.maxAge(1, TimeUnit.DAYS).cachePrivate().proxyRevalidate();
        final HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.put(HttpHeaders.CONTENT_DISPOSITION, List.of("attachment; filename=\"" + resource.getFilename() + "\""));
        if (Objects.nonNull(contentLength)) {
            httpHeaders.put(HttpHeaders.CONTENT_LENGTH, List.of(contentLength));
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .cacheControl(cacheControl)
                .headers(httpHeaders)
                .body(resource);
    }

    @Override
    public Path getPath(String path) {
        return Paths.get(path).normalize().toAbsolutePath();
    }

    @Override
    public Path getPath(String dir, String fileName, String type) {
        return Paths.get(dir.concat(SLASH)
                        .concat(type.toLowerCase(Locale.ROOT))
                        .concat(SLASH)
                        .concat(fileName))
                .normalize().toAbsolutePath();
    }

    @Override
    public File store(MultipartFile file) {
        try {
            final String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
            final String fileName = this.fileStorageUtils
                    .getExtension(originalFilename)
                    .then()
                    .getFileName();
            final File.FileType type = resolveFileType(file);
            final InputStream inputStream = file.getInputStream();
            final Path path = this.getPath(dir, fileName, type.name().concat("s"));
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            final File fileToStore = new File()
                    .setName(fileName)
                    .setPath(path.toString())
                    .setDownloadUrl(this.buidlDownloadUrl(fileName, "files"))
                    .setType(type);
            return this.fileRepository.save(fileToStore);
        } catch (IOException e) {
            String format = String.format("unable to store file fileName : %s", file.getName());
            log.info(format);
            throw new FileStoreException(format);
        }
    }

    @Override
    public Resource download(Path path) {
        try {
            return this.resourceLoader.getResource(path.toString());
        } catch (Exception exception) {
            String format = String.format("unable to store file fileName : %s", path.getFileName());
            log.info(format);
            throw new FileStoreException(format);
        }
    }

    private String buidlDownloadUrl(String fileName, String location) {
        return SLASH.concat(location).concat(SLASH).concat(fileName);
    }

    private File.FileType resolveFileType(MultipartFile file) {
        if (file.getContentType().trim().toLowerCase(Locale.ROOT).startsWith("image")) return File.FileType.IMAGE;
        if (file.getContentType().trim().toLowerCase(Locale.ROOT).startsWith("video")) return File.FileType.VIDEO;
        return File.FileType.OTHER;
    }

    private Consumer<String> createDirs() {
        return fileType -> {
            try {
                final Path absolutePath = this.getPath(dir.concat(SLASH).concat(fileType));
                Files.createDirectories(absolutePath);
            } catch (Exception exception) {
                log.info(String.valueOf(NestedExceptionUtils.getMostSpecificCause(exception)));
            }
        };
    }
}
