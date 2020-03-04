package com.github.rabend.generators;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DoubleGeneratorTest extends AbstractValueGeneratorTest {
    @Test
    public void shouldGenerateRandomValue() {
        // if we have a normal number property
        JsonNode heightNode = propertiesNode.get("height");

        // a random value should be generated
        String randomValue = new DoubleGenerator().generateRandomValue(heightNode);
        Double castValue = Double.valueOf(randomValue);

        assertNotNull(castValue);
        assertTrue(castValue >= 0);
    }

    @Test
    public void shouldGenerateWithExclusiveMinValue() {
        //if we have a property with exclusive min value of 0
        JsonNode itemsperpackageNode = propertiesNode.get("units");

        // then we expect the generated value to be greater 0
        String randomValue = new DoubleGenerator().generateRandomValue(itemsperpackageNode);
        Double castValue = Double.valueOf(randomValue);

        assertNotNull(castValue);
        assertTrue(castValue > 0);
    }

    @Test
    public void shouldGenerateMaximumValue() {
        // if we have a property with a maximum value of 1
        JsonNode fitRatioNode = propertiesNode.get("fitratio");

        // then we expect the generated value to be less than or equal to the maximum
        String randomValue = new DoubleGenerator().generateRandomValue(fitRatioNode);
        Double castValue = Double.valueOf(randomValue);

        assertNotNull(castValue);
        assertTrue("Value was " + castValue, castValue <= 1);
        assertTrue(castValue >= 0);
    }
}
