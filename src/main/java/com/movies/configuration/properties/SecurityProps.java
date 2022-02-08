/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movies.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Chahir Chalouati
 */
@ConfigurationProperties(prefix = "security")
@Data
public class SecurityProps {
    private String secret;
    private Integer expirationTime;
    private String tokenPrefix;
    private String authorizationHeader;
    private String issuer;
}
