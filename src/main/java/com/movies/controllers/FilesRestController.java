package com.movies.controllers;

import com.movies.services.StorageService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FilesRestController {

    private final StorageService storageService;

    @SneakyThrows
    @GetMapping(value = "/{filename:.+}")
    public ResponseEntity<?> files(@PathVariable(name = "filename") String filename, HttpServletRequest request) {
        return storageService.getFile(filename, request);
    }
}
