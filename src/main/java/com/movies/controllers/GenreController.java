package com.movies.controllers;

import com.movies.dtos.Requests.CreateGenreRequest;
import com.movies.dtos.Requests.UpdateGenreRequest;
import com.movies.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
//    @PreAuthorize("hasAuthority('VIEW_GENRE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAll(@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(this.genreService.getGenres(limit));
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('VIEW_GENRE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findOne(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.genreService.findOne(id));
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('CREATE_GENRE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> add(@RequestBody @Valid CreateGenreRequest request) {
        return new ResponseEntity<>(this.genreService.save(request), HttpStatus.CREATED);
    }

    @PutMapping()
//    @PreAuthorize("hasAuthority('UPDATE_GENRE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateGenreRequest request) {
        return new ResponseEntity<>(this.genreService.update(request), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('DELETE_GENRE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        this.genreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
