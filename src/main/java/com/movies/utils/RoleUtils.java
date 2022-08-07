package com.movies.utils;

/**
 * @author Chahir Chalouati
 */
public class RoleUtils {
    public static final String[] DEFAULT_USER_ROLES = {
            "ROLE_USER",
            "CREATE_USER_MOVIE",
            "UPDATE_USER_MOVIE",
            "DELETE_USER_MOVIE",
            "VIEW_USER_MOVIE",
            "LISTEN_USER_AUDIO"
    };

    public static final String[] DEFAULT_ADMIN_ROLES = {
            "AUDIO_ADMIN_ALL",
            "MOVIE_ADMIN_ALL",
            "USER_ADMIN_ALL"
    };
}
