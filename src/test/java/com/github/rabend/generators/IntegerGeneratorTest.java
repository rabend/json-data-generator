package com.github.rabend.generators;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class IntegerGeneratorTest extends AbstractValueGeneratorTest{
    @Test
    public void shouldGenerateRandomValueGreaterThan0() {
        // if we have a property with no bounds
        JsonNode weightNode = propertiesNode.get("weight");

        //then we expect the value to be greater 0 and less than Integer.MAX_VALUE
        String randomValue = new IntegerGenerator().generateRandomValue(weightNode);
        Integer castValue = Integer.valueOf(randomValue);

        assertNotNull(castValue);
        assertTrue("Value was less or equal 0", castValue >= 0);
        assertTrue( castValue <= Integer.MAX_VALUE);
    }

    @Test
    public void shouldGenerateMinValue() {
        // if we have a property with a min value of 200
        JsonNode versionNode = propertiesNode.get("version");

        // then we expect the generated value not to be greater or equal to the minimum
        String randomValue = new IntegerGenerator().generateRandomValue(versionNode);
        Integer castValue = Integer.valueOf(randomValue);

        assertNotNull(castValue);
        assertTrue("Value was " + castValue, castValue >= 200);
    }
}
