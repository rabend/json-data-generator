package com.github.rabend.generators;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ArrayGenerator extends AbstractValueGenerator {
    @Override
    public String generateRandomValue(final JsonNode node) {
        JsonNode itemsNode = node.get("items");
        AbstractValueGenerator itemGenerator = ValueGeneratorsLookup.getGeneratorForType(itemsNode.get("type").asText());

        int arrayCount = ThreadLocalRandom.current().nextInt(6);

        if (itemsNode.has("enum")) {
            arrayCount = itemsNode.withArray("enum").size();
        }

        Set<String> values = new HashSet<>();
        for (int i = 0; i < arrayCount; i++) {
            values.add(itemGenerator.generateRandomValue(itemsNode));
        }

        return values.stream().collect(Collectors.joining(",", "[", "]"));
    }
}
