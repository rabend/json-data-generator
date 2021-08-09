package com.github.rabend.generators;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.concurrent.ThreadLocalRandom;

public class DoubleGenerator extends AbstractValueGenerator {
    @Override
    public String generateRandomValue(final JsonNode node) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        Double minVal = 0.0;
        Double maxVal = Double.MAX_VALUE;
        boolean exclusive = false;
        if (node.has("minimum")) {
            if (node.has("exclusiveMinimum")) {
                exclusive = node.get("exclusiveMinimum").asBoolean();
            }

            minVal = node.get("minimum").asDouble();

            if (exclusive) {
                minVal++;
            }
        }

        if (node.has("maximum")) {
            maxVal = node.get("maximum").asDouble();
        }

        Double randomDouble = random.nextDouble(minVal, maxVal);
        return randomDouble.toString();
    }
}
