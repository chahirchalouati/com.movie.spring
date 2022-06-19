/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movies.dtos.Responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author Chahir Chalouati
 */
@Data
@AllArgsConstructor
public class JWTResponse {

    private String token;
    @JsonIgnore
    private String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date generatedAt = new Date(System.currentTimeMillis());

    public JWTResponse(String token) {
        this.token = token;
    }
}
