package com.movies.controllers;

import com.movies.DTOs.Requests.CreateMovieRequest;
import com.movies.DTOs.Requests.UpdateMovieRequest;
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
//    @PreAuthorize("hasAuthority('VIEW_MOVIE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.movieService.findAll(pageable));
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('VIEW_MOVIE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findOne(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.movieService.getOne(id));
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @PreAuthorize("hasAuthority('CREATE_MOVIE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> add(@Valid CreateMovieRequest createMovieRequest) {
        return new ResponseEntity<>(this.movieService.add(createMovieRequest), HttpStatus.CREATED);
    }

    @PutMapping()
//    @PreAuthorize("hasAuthority('UPDATE_MOVIE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@Valid UpdateMovieRequest updateMovieRequest) {
        return new ResponseEntity<>(this.movieService.update(updateMovieRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('DELETE_MOVIE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        this.movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/title/{title}")
//    @PreAuthorize("hasAuthority('DELETE_MOVIE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteByTitle(@PathVariable String title) {
        this.movieService.deleteByTitle(title);
        return ResponseEntity.noContent().build();
    }
}
