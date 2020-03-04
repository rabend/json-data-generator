package com.github.rabend.generators;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ObjectGeneratorTest extends AbstractValueGeneratorTest {
    @Test
    public void shouldGenerateAnObjectAccordingToSchema() throws IOException {
        // if we have a type 'object' somewhere
        JsonNode gtinNode = propertiesNode.get("eans").get("items");

        // then we expect correct objects to be generated for the underlying schema
        String randomObject = new ObjectGenerator().generateRandomValue(gtinNode);
        JsonNode gtin = mapper.readTree(randomObject);

        assertTrue(gtin.get("ean").isTextual());
        assertTrue(gtin.get("type").isDouble());
    }
}
