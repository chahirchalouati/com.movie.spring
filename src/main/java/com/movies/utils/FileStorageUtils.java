package com.movies.utils;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
public class FileStorageUtils {

    private static final Map<String, ImageDimension> dimensionMap = new HashMap<>();

    public static Map<String, ImageDimension> getDimensionMap() {
        dimensionMap.put("xl", ImageDimension.builder().width(1022).height(620).build());
        dimensionMap.put("lg", ImageDimension.builder().width(740).height(400).build());
        dimensionMap.put("md", ImageDimension.builder().width(165).height(165).build());
        dimensionMap.put("sm", ImageDimension.builder().width(150).height(150).build());
        dimensionMap.put("xs", ImageDimension.builder().width(80).height(80).build());
        return dimensionMap;
    }

    public static String generateRandomName() {
        return UUID.randomUUID().toString();
    }

    public static String buildFileName(String extension) {
        return generateRandomName().concat(".").concat(extension);
    }

    public static String buildFileName(String prefix, String extension) {
        return generateRandomName().concat(prefix).concat(".").concat(extension);
    }

    public static Path buildPath(String baseDirectory, String fileName) {
        return Paths.get(baseDirectory.concat("/").concat(fileName)).toAbsolutePath().normalize();
    }

    public static Path getPath(String storeLocation) {
        return Paths.get(storeLocation).toAbsolutePath().normalize();
    }

    public static String buildFileName(String extension, String... fields) {
        final String reduce = Arrays.stream(fields).map(s -> s.concat("_")).reduce(String::concat).orElse("");
        return generateRandomName().concat(reduce).concat(".").concat(extension);
    }

    public static String getExtension(String originalFilename) {
        return Objects.requireNonNull(originalFilename).substring(originalFilename.length() - 3);
    }

    public static String getContentLength(Resource resource) {
        try {
            return String.valueOf(resource.contentLength());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static String getContentType(HttpServletRequest request, Resource resource) {
        try {
            return request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
    }

    public static void createDirectory(String baseDirectory) throws IOException {
        if (!Files.exists(Paths.get(baseDirectory).toAbsolutePath().normalize()))
            Files.createDirectories(Paths.get(baseDirectory).toAbsolutePath().normalize());
    }

    public static Resource getResource(Path path) {
        try {
            return new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Builder
    @Data
    @Accessors(fluent = true)
    public static class ImageDimension {
        private Integer width;
        private Integer height;
    }
}
