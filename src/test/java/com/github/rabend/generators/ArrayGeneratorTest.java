package com.github.rabend.generators;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayGeneratorTest extends AbstractValueGeneratorTest {

    @Test
    public void shouldGenerateSimpleArrayOfValues() throws IOException {
        // if we have a property that is of type array
        JsonNode customFlagsNode = propertiesNode.get("producttags");

        // then we expect an array with random length and random items of the specified type
        String randomValue = new ArrayGenerator().generateRandomValue(customFlagsNode);
        JsonNode arrayNode = mapper.readTree(randomValue);

        assertTrue(arrayNode.isArray());
        arrayNode.elements().forEachRemaining(node -> assertTrue(node.isTextual()));
    }

    @Test
    public void shouldGenerateArrayForEnum() throws IOException {
        // if we have a property that is an array with an underlying enum
        JsonNode productFlagNode = propertiesNode.get("flags");

        // then we expect the generated array to only contain values from that enum once
        String randomValue = new ArrayGenerator().generateRandomValue(productFlagNode);
        JsonNode arrayNode = mapper.readTree(randomValue);

        assertTrue(arrayNode.isArray());
        List<String> validFlags = Arrays.asList("org", "foo", "bar", "baz");

        arrayNode.elements().forEachRemaining(node -> {
            assertTrue(validFlags.contains(node.asText()));
            validFlags.remove(node);
            assertFalse(validFlags.contains(node));
        });
    }
}
