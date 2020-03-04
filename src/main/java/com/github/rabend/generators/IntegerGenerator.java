package com.github.rabend.generators;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.concurrent.ThreadLocalRandom;

public class IntegerGenerator extends AbstractValueGenerator {
    @Override
    public String generateRandomValue(final JsonNode node) {
        Integer randomInt;
        ThreadLocalRandom random = ThreadLocalRandom.current();

        if (node.has("minimum")) {
            randomInt = random.nextInt(node.get("minimum").asInt(), Integer.MAX_VALUE);
            return randomInt.toString();
        }

        randomInt = random.nextInt(0, Integer.MAX_VALUE);
        return randomInt.toString();
    }
}
