package com.movies.controllers;

import com.movies.DTOs.Responses.UserResponse;
import com.movies.services.ProfileService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProfileController.class)
public class ProfileControllerTest {
    @MockBean
    ProfileService profileService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_givenUserId_whenGetProfile_thenOk() throws Exception {
        final UserResponse userResponse = new UserResponse();
        userResponse.setId("4600de6d-b396-4d12-bdcd-4da91a2e0230");
        userResponse.setUserName("Kennth");
        userResponse.setLastName("Falconer");
        userResponse.setFirstName("Candy");
        when(profileService.getOne(any())).thenReturn(new UserResponse());

        mockMvc.perform(get("/profiles/4600de6d-b396-4d12-bdcd-4da91a2e0230"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}