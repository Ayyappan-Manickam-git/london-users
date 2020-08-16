package com.demo.london.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.StreamSupport;

@JsonComponent
public class Deserializer extends JsonDeserializer<UserSet>  {

    @Override
    public UserSet deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ArrayNode arrayNode = jsonParser.getCodec().readTree(jsonParser);

        Set<User> userSet = new HashSet<>();

        StreamSupport
                .stream(arrayNode.spliterator(), false)
                .forEach(userNode -> userSet.add(getUser(userNode)));

        return new UserSet(userSet);
    }

    private User getUser(TreeNode treeNode) {
        return new User(getInteger(treeNode, "id"),
                getText(treeNode, "first_name"),
                getText(treeNode, "last_name"),
                getText(treeNode, "email"),
                getText(treeNode, "ip_address"),
                getText(treeNode, "latitude"),
                getText(treeNode, "longitude"),
                getText(treeNode, "city"));
    }

    public String getText(TreeNode treeNode, String path) {
        ValueNode node = (ValueNode) treeNode.get(path);
        return node == null || node.isNull() ? null : node.asText().trim();
    }

    public Integer getInteger(TreeNode treeNode, String path) {
        ValueNode node = (ValueNode) treeNode.get(path);
        return node == null ? null : node.asInt();
    }
}
