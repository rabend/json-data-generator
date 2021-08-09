package com.github.rabend.generators;

import com.fasterxml.jackson.databind.JsonNode;
import com.mifmif.common.regex.Generex;

import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator extends AbstractValueGenerator {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ ";
    private static final String LOWERCASE = UPPERCASE.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS;
    public static final String FORMAT = "format";


    @Override
    public String generateRandomValue(final JsonNode node) {
        if (node.has(FORMAT)) {
            if (node.get(FORMAT).asText().equalsIgnoreCase("date")) {
                return "\"" + new SimpleDateGenerator().generateDate().toString() + "\"";
            } else if (node.get(FORMAT).asText().equalsIgnoreCase("date-time")) {
                return "\"" + new SimpleDateGenerator().generateDateTime().toString() + "\"";
            }
        }

        int maxLength = 50;
        int minLength = 6;

        if (node.has("maxLength")) {
            maxLength = node.get("maxLength").asInt() + 1;
            minLength = 0;
        }

        if (node.has("minLength")) {
            minLength = node.get("minLength").asInt();
        }

        if (node.has("pattern")) {
            String regex = node.get("pattern").asText();
            regex = regex.replace("^", "");
            regex = regex.replace("$", "");
            Generex generex = new Generex(regex);
            return "\"" + generex.random(minLength, maxLength - 1) + "\"";
        }

        ThreadLocalRandom random = ThreadLocalRandom.current();
        if (node.has("enum")) {
            JsonNode enumNode = node.withArray("enum");
            int bound = enumNode.size() == 1 ? 1 : enumNode.size() - 1;
            int randomIndex = random.nextInt(bound);
            return "\"" + enumNode.get(randomIndex).asText() + "\"";
        }

        int randomLength = random.nextInt(minLength, maxLength);

        char[] chars = new char[randomLength];
        for (int i = 0; i < randomLength; i++) {
            int bound = random.nextInt(ALL_CHARS.length() - 1);
            chars[i] = ALL_CHARS.toCharArray()[bound];
        }

        return "\"" + new String(chars) + "\"";
    }
}
