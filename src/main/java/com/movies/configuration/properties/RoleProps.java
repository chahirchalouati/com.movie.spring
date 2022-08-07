/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movies.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

/**
 * @author Chahir Chalouati
 */
@ConfigurationProperties(prefix = "roles")
@Data
public class RoleProps {
    private Set<String> userRoles;
    private Set<String> adminRoles;

}
