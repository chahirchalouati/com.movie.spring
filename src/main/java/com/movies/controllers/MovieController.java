package com.movies.controllers;

import com.movies.DTOs.Requests.CreateMovieRequest;
import com.movies.DTOs.Requests.UpdateMovieRequest;
import com.movies.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('CREATE_MOVIE') and hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> add(CreateMovieRequest createMovieRequest) {
        return ResponseEntity.ok(this.movieService.add(createMovieRequest));
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('UPDATE_MOVIE') and hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(UpdateMovieRequest updateMovieRequest) {
        return ResponseEntity.ok(this.movieService.update(updateMovieRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_MOVIE') and hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        return ResponseEntity.ok(this.movieService.deleteById(id));
    }

    @DeleteMapping("/title/{title}")
    @PreAuthorize("hasAuthority('DELETE_MOVIE') and hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteByTitle(@PathVariable String title) {
        return ResponseEntity.ok(this.movieService.deleteByTitle(title));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_MOVIE')")
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.movieService.findAll(pageable));
    }


}
