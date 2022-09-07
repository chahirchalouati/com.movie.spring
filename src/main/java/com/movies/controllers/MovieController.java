package com.movies.controllers;

import com.movies.dtos.Requests.CreateMovieRequest;
import com.movies.dtos.Requests.UpdateMovieRequest;
import com.movies.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.movieService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.movieService.getOne(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<?> findCode(@PathVariable("code") Integer code) {
        return ResponseEntity.ok(this.movieService.getByCode(code));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> add(@Valid CreateMovieRequest createMovieRequest) {
        return new ResponseEntity<>(this.movieService.add(createMovieRequest), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid UpdateMovieRequest updateMovieRequest) {
        return new ResponseEntity<>(this.movieService.update(updateMovieRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        this.movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/title/{title}")
    public ResponseEntity<?> deleteByTitle(@PathVariable String title) {
        this.movieService.deleteByTitle(title);
        return ResponseEntity.noContent().build();
    }
}
