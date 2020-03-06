package com.github.rabend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rabend.generators.ObjectGenerator;

import java.io.IOException;
import java.net.URL;

public class TestDataGenerator {

    private final URL schemaUrl;
    private final ObjectMapper mapper;

    /**
     * Constructor
     * @param schemaUrl URL to a valid json schema
     */
    public TestDataGenerator(final URL schemaUrl) {
        this.schemaUrl = schemaUrl;
        this.mapper = new ObjectMapper();
    }

    /**
     * Generates a json string with random values for the keys defined in the json schema.
     * Values will be generated according to any restrictions in the schema, if present.
     * @return a json string
     * @throws IOException
     */
    public String generateJsonString() throws IOException {
        JsonNode baseNode = mapper.readTree(this.schemaUrl);
        return new ObjectGenerator().generateRandomValue(baseNode);
    }
}
