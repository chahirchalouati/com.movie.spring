package com.movies.controllers;

import com.movies.dtos.Requests.UpdateUserRequest;
import com.movies.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> findAll(Pageable pageable) {
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<?> findByUserName(@PathVariable String username) {
        return new ResponseEntity<>(userService.findByUserName(username), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid UpdateUserRequest updateUserRequest) {
        return new ResponseEntity<>(userService.update(updateUserRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        return new ResponseEntity<>(this.userService.deleteById(id), HttpStatus.OK);
    }

    @DeleteMapping("/username/{username}")
    public ResponseEntity<?> deleteByUserName(@PathVariable String username) {
        return new ResponseEntity<>(this.userService.deleteByUserName(username), HttpStatus.OK);
    }
}
