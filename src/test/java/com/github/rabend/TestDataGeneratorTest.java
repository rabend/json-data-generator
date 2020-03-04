package com.github.rabend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;

public class TestDataGeneratorTest {
    @Test
    public void twoAttributeNodesShouldNotBeEqual() throws IOException {
        TestDataGenerator generator = new TestDataGenerator(this.getClass().getResource("/schema/Article.json"));

        String attributeString1 = generator.generateJsonString();
        JsonNode attributes1 = new ObjectMapper().readTree(attributeString1);

        String attributeString2 = generator.generateJsonString();
        JsonNode attributes2 = new ObjectMapper().readTree(attributeString2);

        assertNotEquals(attributes1, attributes2);
    }
}
