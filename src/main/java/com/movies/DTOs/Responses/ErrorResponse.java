package com.movies.DTOs.Responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Map;

@NoArgsConstructor
@Data
public class ErrorResponse {
    @JsonProperty(value = "message", index = 1)
    private String defaultErrorMessage = "bad request";
    @JsonProperty(value = "errors", index = 2)
    private Map<String, String> errors;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS", shape = JsonFormat.Shape.STRING)
    @JsonProperty(index = 3)
    private LocalDateTime timestamp = LocalDateTime.now(Clock.systemDefaultZone());

    public ErrorResponse(String defaultErrorMessage, Map<String, String> errors) {
        this.defaultErrorMessage = defaultErrorMessage;
        this.errors = errors;
    }

    public ErrorResponse(Map<String, String> errors) {
        this.errors = errors;
    }

    public ErrorResponse(String defaultErrorMessage) {
        this.defaultErrorMessage = defaultErrorMessage;
    }
}
