package com.demo.london.user;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = Serializer.class)
class SerializerTest {

    @Autowired
    private Serializer serializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    @Mock
    private User user;

    @Test
    void serialize() throws IOException {
        when(user.getId()).thenReturn(1);
        when(user.getFirstName()).thenReturn("firstName");
        when(user.getLastName()).thenReturn("lastName");
        when(user.getEmail()).thenReturn("email");
        when(user.getIpAddress()).thenReturn("ip");
        when(user.getLatitude()).thenReturn("12");
        when(user.getLongitude()).thenReturn("25");
        when(user.getCity()).thenReturn("Newcastle");

        serializer.serialize(user, jsonGenerator, serializerProvider);

        verify(user).getId();
        verify(user).getFirstName();
        verify(user).getLastName();
        verify(user).getEmail();
        verify(user).getIpAddress();
        verify(user).getLatitude();
        verify(user).getLongitude();
        verify(user).getCity();

        verify(jsonGenerator).writeStartObject();
        verify(jsonGenerator).writeEndObject();

        verify(jsonGenerator).writeNumberField("id", 1);
        verify(jsonGenerator).writeStringField("first_name", "firstName");
        verify(jsonGenerator).writeStringField("last_name", "lastName");
        verify(jsonGenerator).writeStringField("email", "email");
        verify(jsonGenerator).writeStringField("ip_address", "ip");
        verify(jsonGenerator).writeStringField("latitude", "12");
        verify(jsonGenerator).writeStringField("longitude", "25");
        verify(jsonGenerator).writeStringField("city", "Newcastle");

        verifyNoMoreInteractions(user, jsonGenerator);
    }
}