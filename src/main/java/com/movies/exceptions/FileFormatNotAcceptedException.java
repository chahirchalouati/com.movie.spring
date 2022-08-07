package com.movies.exceptions;

/**
 * @author Chahir Chalouati
 */
public class FileFormatNotAcceptedException extends RuntimeException {
    public FileFormatNotAcceptedException(String message) {
        super(message);
    }
}
