package com.movies.controllers;

import com.movies.DTOs.Requests.CreateProfileRequest;
import com.movies.DTOs.Requests.UpdateProfileRequest;
import com.movies.DTOs.Responses.UserResponse;
import com.movies.services.ProfileService;
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
@RequestMapping("/profiles")
public class ProfileController {

    private ProfileService profileService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> get(@PathVariable(name = "userId") String userId) {
        return new ResponseEntity<>(this.profileService.getOne(userId), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponse> add(@Valid CreateProfileRequest createProfileRequest) {
        return new ResponseEntity<>(this.profileService.add(createProfileRequest), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponse> update(@Valid UpdateProfileRequest updateProfileRequest) {
        return new ResponseEntity<>(this.profileService.update(updateProfileRequest), HttpStatus.OK);
    }

}
