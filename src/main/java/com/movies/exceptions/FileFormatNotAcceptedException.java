package com.movies.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
public class FileFormatNotAcceptedException extends RuntimeException {
    public FileFormatNotAcceptedException(String message) {
        super(message);
    }
}
