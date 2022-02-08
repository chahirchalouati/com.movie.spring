package com.movies.services.Impl;

import com.movies.DTOs.Requests.CreateProfileRequest;
import com.movies.DTOs.Requests.UpdateProfileRequest;
import com.movies.DTOs.Responses.UserResponse;
import com.movies.domain.File;
import com.movies.domain.Profile;
import com.movies.domain.User;
import com.movies.exceptions.EntityAlreadyExistsException;
import com.movies.exceptions.EntityNotFoundException;
import com.movies.mappers.UserMapper;
import com.movies.repositories.ProfileRepository;
import com.movies.repositories.UserRepository;
import com.movies.services.ProfileService;
import com.movies.services.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Chahir Chalouati
 */
@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    /**
     * @implNote nobody has the right to delete any profile
     * so  deleting logic should not be implemented
     */

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final StorageService storageService;

    @Transactional(readOnly = true)
    @Override
    public UserResponse getOne(String userId) {
        // TODO: 2/8/2022 add a check to validate that only the current logged user can get back information about his profile
        final Profile profile = this.profileRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException(String.format("unable to find profile with userId: %s", userId)));
        final User user = this.userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(String.format("unable to find user with userId: %s", userId)));
        return this.userMapper.mapToUserResponse(user, profile);
    }

    @Transactional
    @Override
    public UserResponse add(CreateProfileRequest createProfileRequest) {
        // TODO: 2/8/2022 add a check to validate that only the current logged user can create his own profile
        this.profileRepository.findByUserId(createProfileRequest.getUserId()).ifPresent(profile -> {
            throw new EntityAlreadyExistsException("profile exists for user wih id : " + createProfileRequest.getUserId());
        });
        final User user = this.userRepository.findById(createProfileRequest.getUserId()).orElseThrow(() -> new EntityNotFoundException(String.format("unable to find user with userId: %s", createProfileRequest.getUserId())));
        final File storedFile = this.storageService.store(createProfileRequest.getAvatar());
        final Profile profile = new Profile();
        profile.setAvatar(storedFile.getDownloadUrl());
        profile.setUserId(createProfileRequest.getUserId());
        final Profile storedProfile = this.profileRepository.save(profile);
        return this.userMapper.mapToUserResponse(user, storedProfile);
    }

    @Transactional
    @Override
    public UserResponse update(UpdateProfileRequest updateProfileRequest) {
        // TODO: 2/8/2022 add a check to validate that only the current logged user can update his own profile
        final Profile profile = this.profileRepository.findById(updateProfileRequest.getId()).orElseThrow(() -> new EntityNotFoundException(String.format("unable to find profile with userId: %s", updateProfileRequest.getId())));
        final File storedFile = this.storageService.store(updateProfileRequest.getAvatar());
        profile.setAvatar(storedFile.getDownloadUrl());
        final Profile storedProfile = this.profileRepository.save(profile);
        final User user = this.userRepository.findById(profile.getUserId()).orElseThrow(() -> new EntityNotFoundException(String.format("unable to find user with userId: %s", profile.getUserId())));
        return this.userMapper.mapToUserResponse(user, storedProfile);
    }
}
