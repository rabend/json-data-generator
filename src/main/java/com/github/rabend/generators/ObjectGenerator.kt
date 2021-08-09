package com.github.rabend.generators;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ObjectGenerator extends AbstractValueGenerator {
    @Override
    public String generateRandomValue(final JsonNode node) {
        Map<String, AbstractValueGenerator> propertyToGenerator = new HashMap<>();
        if (node.has("properties")) {

            JsonNode propertiesNode = node.get("properties");

            propertiesNode.fields()
                    .forEachRemaining(propAndNode ->
                            propertyToGenerator.put(propAndNode.getKey(),
                                    ValueGeneratorsLookup.getGeneratorForType(propAndNode.getValue().get("type").asText())));

            return propertyToGenerator.entrySet()
                    .stream()
                    .map(propToGen -> {
                        String propName = propToGen.getKey();
                        return createKey(propName) + propToGen.getValue().generateRandomValue(propertiesNode.get(propName));
                    })
                    .collect(Collectors.joining(",", "{", "}"));
        } else {
            return "{}";
        }
    }

    private String createKey(final String prop) {
        return "\"" + prop + "\":";
    }
}
