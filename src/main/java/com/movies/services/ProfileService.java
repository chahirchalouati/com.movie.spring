package com.movies.services;

import com.movies.dtos.Requests.CreateProfileRequest;
import com.movies.dtos.Requests.UpdateProfileRequest;
import com.movies.dtos.Responses.UserResponse;

/**
 * @author Chahir Chalouati
 */
public interface ProfileService {

    UserResponse getOne(String userId);

    UserResponse add(CreateProfileRequest createProfileRequest);

    UserResponse update(UpdateProfileRequest updateProfileRequest);

    void addDefault(String id);
}
