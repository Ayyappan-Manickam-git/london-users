package com.demo.london.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Deserializer.class)
class DeserializerTest {

    @Mock
    JsonParser jsonParser;

    @Mock
    DeserializationContext deserializationContext;

    @Mock
    ObjectCodec objectCodec;

    @Mock
    private JsonNodeFactory jsonNodeFactory;

    @Mock
    JsonNode jsonNode;

    private List<JsonNode> listOfJsonNodes;

    @Test
    void deserialize() throws IOException {
        Deserializer localDeserializer = spy(new Deserializer());
        listOfJsonNodes = new ArrayList<>();
        listOfJsonNodes.add(jsonNode);
        var arrayNode = new ArrayNode(jsonNodeFactory, listOfJsonNodes);
        when(jsonParser.getCodec()).thenReturn(objectCodec);
        when(objectCodec.readTree(jsonParser)).thenReturn(arrayNode);

        doReturn(1).when(localDeserializer).getInteger(jsonNode, "id");
        doReturn("firstName").when(localDeserializer).getText(jsonNode, "first_name");
        doReturn("LastName").when(localDeserializer).getText(jsonNode, "last_name");
        doReturn("email").when(localDeserializer).getText(jsonNode, "email");
        doReturn("ip").when(localDeserializer).getText(jsonNode, "ip_address");
        doReturn("latitude").when(localDeserializer).getText(jsonNode, "latitude");
        doReturn("longitude").when(localDeserializer).getText(jsonNode, "longitude");
        doReturn("city").when(localDeserializer).getText(jsonNode, "city");

        var result = localDeserializer.deserialize(jsonParser, deserializationContext);

        assertNotNull(result);
        assertEquals(1, result.getUserSet().size());

        result.getUserSet().forEach(user -> {
            assertEquals(1, user.getId());
            assertEquals("firstName", user.getFirstName());
            assertEquals("LastName", user.getLastName());
            assertEquals("email", user.getEmail());
            assertEquals("ip", user.getIpAddress());
            assertEquals("latitude", user.getLatitude());
            assertEquals("longitude", user.getLongitude());
            assertEquals("city", user.getCity());
        });
    }
}