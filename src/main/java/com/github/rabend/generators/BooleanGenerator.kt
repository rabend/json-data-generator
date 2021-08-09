package com.github.rabend.generators;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.concurrent.ThreadLocalRandom;

public class BooleanGenerator extends AbstractValueGenerator {
    @Override
    public String generateRandomValue(final JsonNode node) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        int randomInt = random.nextInt(0, 2);

        return String.valueOf(randomInt == 0);
    }
}
