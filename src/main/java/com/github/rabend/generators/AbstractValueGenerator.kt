package com.github.rabend.generators;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class AbstractValueGenerator {
    public abstract String generateRandomValue(final JsonNode node);
}
