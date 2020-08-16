package com.demo.london.user;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class Serializer extends JsonSerializer<User> {

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        writeNumberField(jsonGenerator, "id", user.getId());
        writeStringField(jsonGenerator, "first_name", user.getFirstName());
        writeStringField(jsonGenerator, "last_name", user.getLastName());
        writeStringField(jsonGenerator, "email", user.getEmail());
        writeStringField(jsonGenerator, "ip_address", user.getIpAddress());
        writeStringField(jsonGenerator, "latitude", user.getLatitude());
        writeStringField(jsonGenerator, "longitude", user.getLongitude());
        writeStringField(jsonGenerator, "city", user.getCity());

        jsonGenerator.writeEndObject();
    }

    private void writeStringField(JsonGenerator jsonGenerator, String fieldName, String value) throws IOException {
        if (value != null) {
            jsonGenerator.writeStringField(fieldName, value);
        }
    }

    private void writeNumberField(JsonGenerator jsonGenerator, String fieldName, Integer value) throws IOException {
        if (value != null) {
            jsonGenerator.writeNumberField(fieldName, value);
        }
    }
}
