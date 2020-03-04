package com.github.rabend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rabend.generators.ObjectGenerator;

import java.io.IOException;
import java.net.URL;

public class TestDataGenerator {

    private final URL schemaUrl;
    private final ObjectMapper mapper;

    public TestDataGenerator(final URL schemaUrl) {
        this.schemaUrl = schemaUrl;
        this.mapper = new ObjectMapper();
    }

    public String generateJsonString() throws IOException {
        JsonNode baseNode = mapper.readTree(this.schemaUrl);
        return new ObjectGenerator().generateRandomValue(baseNode);
    }
}
