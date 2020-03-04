package com.github.rabend.generators;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StringGeneratorTest extends AbstractValueGeneratorTest {

    @Test
    public void shouldGeneratorRandomStringForSimpleStringProp() throws IOException {
        //if we have a string property with no additional info
        JsonNode nameNode = propertiesNode.get("name");

        //we want to generate a random string with random length between 6 and 200
        String randomString = new StringGenerator().generateRandomValue(nameNode);
        randomString = randomString.substring(1, randomString.length() - 1);

        assertNotNull(randomString);
        assertTrue("Generated value was too short!",randomString.length() >= 6);
        assertTrue("Generated value was too long!",randomString.length() <= 50);
    }

    @Test
    public void shouldGenerateKeyWithMaxLength() {
        // if we have a string with a max length of 2
        JsonNode countryNode = propertiesNode.get("country");

        // then we expect the generated value to not exceed the max length
        String randomString = new StringGenerator().generateRandomValue(countryNode);
        randomString = randomString.substring(1, randomString.length() - 1);

        assertNotNull(randomString);
        assertTrue("Length was " + randomString.length(), randomString.length() <= 2);
    }

    @Test
    public void shouldGenerateMinLength() {
        // if we have a string with a min length of 18
        JsonNode deposittypeNode = propertiesNode.get("producttype");

        // then we expect the generated value to not be shorter than the min length
        String randomString = new StringGenerator().generateRandomValue(deposittypeNode);
        randomString = randomString.substring(1, randomString.length() - 1);

        assertNotNull(randomString);
        assertTrue("Generated value was too short!", randomString.length() >= 18);
    }

    @Test
    public void shouldGenerateMinAndMaxLength() {
        // if we have an article with a min length of 4 and a max length of 6
        JsonNode pluNode = propertiesNode.get("plu");

        // then we expect the generated string to be inside those bounds
        String randomString = new StringGenerator().generateRandomValue(pluNode);
        assertNotNull(randomString);
        randomString = randomString.substring(1, randomString.length() - 1);
        assertTrue("Length was " + randomString.length(), randomString.length() >= 4);
        assertTrue("Length was " + randomString.length(), randomString.length() <= 6);
    }

    @Test
    public void shouldGenerateValueForEnum() {
        // if we have an article with a string enum
        JsonNode packagingTypeNode = propertiesNode.get("packagingtype");

        // then we expect the generated value to be an element of the given enum
        JsonNode arrayNode = packagingTypeNode.withArray("enum");
        String randomString = new StringGenerator().generateRandomValue(packagingTypeNode);
        randomString = randomString.substring(1, randomString.length() - 1);

        assertNotNull(randomString);
        boolean contained = false;
        Iterator<JsonNode> elements = arrayNode.elements();
        while (elements.hasNext()) {
            if (elements.next().asText().equals(randomString)) {
                contained = true;
            }
        }
        assertTrue("Value was " + randomString, contained);
    }

    @Test
    public void shouldGenerateRandomDateString() {
        // if we have a property that has format: date
        JsonNode onlineNode = propertiesNode.get("onlinesince");

        // then we want a random date string representation to be generated
        String randomDate = new StringGenerator().generateRandomValue(onlineNode);
        Pattern pattern = Pattern.compile("^([12][0-9]{3})-(0[1-9]|1[0-2])-([0-2][0-9]|3[01])");
        randomDate = randomDate.substring(1, randomDate.length() - 1);
        Matcher matcher = pattern.matcher(randomDate);
        assertTrue(randomDate + " does not match pattern!", matcher.find());
    }

    @Test
    public void shouldGenerateRandomDateTimeString() {
        // if we have a property that has format: date-time
        JsonNode onlineNode = propertiesNode.get("promotionuntil");

        // then we want a random date-time string representation to be generated
        String randomDateTime = new StringGenerator().generateRandomValue(onlineNode);
        Pattern pattern = Pattern.compile("^([12][0-9]{3})-(0[1-9]|1[0-2])-([0-2][0-9]|3[01])T([01][0-9]|2[0-4]):([0-5][0-9]):([0-5][0-9])");
        randomDateTime = randomDateTime.substring(1, randomDateTime.length() - 1);
        Matcher matcher = pattern.matcher(randomDateTime);
        assertTrue(randomDateTime + " does not match pattern!", matcher.find());
    }

    @Test
    public void shouldGenerateStringThatMatchesPattern() {
        // if we have a string property with a pattern
        JsonNode pluNode = propertiesNode.get("plu");

        // then we expect the value to match the pattern
        String randomString = new StringGenerator().generateRandomValue(pluNode);
        randomString = randomString.substring(1, randomString.length() - 1);
        Pattern pattern = Pattern.compile(pluNode.get("pattern").asText());
        Matcher matcher = pattern.matcher(randomString);
        assertTrue(randomString + " does not match pattern!", matcher.find());
    }

    @Test
    public void shouldGenerateForPatternWithMaxLength() {
        // if we have a property with a pattern and a max length
        JsonNode nanNode = propertiesNode.get("sku");

        // the generated value should match the pattern and not exceed the max length
        String randomString = new StringGenerator().generateRandomValue(nanNode);
        randomString = randomString.substring(1, randomString.length() - 1);
        Pattern pattern = Pattern.compile(nanNode.get("pattern").asText());
        Matcher matcher = pattern.matcher(randomString);
        assertTrue(randomString + " does not match pattern!", matcher.find());
        assertTrue("Generated value was too long!", randomString.length() <= nanNode.get("maxLength").asInt());
    }
}
