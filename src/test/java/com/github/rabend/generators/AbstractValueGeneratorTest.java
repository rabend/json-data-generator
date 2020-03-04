package com.github.rabend.generators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;

import java.io.IOException;

public class AbstractValueGeneratorTest {
    protected ObjectMapper mapper;
    protected JsonNode propertiesNode;

    @Before
    public void setUp() throws IOException {
        this.mapper = new ObjectMapper();
        this.propertiesNode = mapper.readTree(this.getClass().getResource("/schema/Article.json")).get("properties");
    }
}
