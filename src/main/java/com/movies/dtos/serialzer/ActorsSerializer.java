package com.movies.dtos.serialzer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.movies.dtos.Responses.UserResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Set;

public class ActorsSerializer extends JsonSerializer<Set<UserResponse>> {

    @Override

    public void serialize(Set<UserResponse> userResponses, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        userResponses.stream().forEach(roleResponses -> {
            try {
                roleResponses.setRoles(null);
                jsonGenerator.writeObject(roleResponses);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        jsonGenerator.writeEndObject();
    }
}
