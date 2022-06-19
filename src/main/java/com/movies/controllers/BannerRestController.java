package com.movies.controllers;

import com.movies.dtos.Requests.BannerCreateRequest;
import com.movies.services.BannerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@RestController
@RequestMapping("/banners")
public class BannerRestController {

    private final BannerService bannerService;

    @GetMapping
    public ResponseEntity<?> files(@RequestParam(name = "limit", required = false, defaultValue = "5") int limit) {
        return new ResponseEntity<>(bannerService.getBanners(limit), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@Valid BannerCreateRequest request) {
        return new ResponseEntity<>(bannerService.save(request), HttpStatus.OK);
    }
}
