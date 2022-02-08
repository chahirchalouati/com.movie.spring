package com.movies.services;

import com.movies.DTOs.Requests.CreateProfileRequest;
import com.movies.DTOs.Requests.UpdateProfileRequest;
import com.movies.DTOs.Responses.UserResponse;

/**
 * @author Chahir Chalouati
 */
public interface ProfileService {

    UserResponse getOne(String userId);

    UserResponse add(CreateProfileRequest createProfileRequest);

    UserResponse update(UpdateProfileRequest updateProfileRequest);
}
