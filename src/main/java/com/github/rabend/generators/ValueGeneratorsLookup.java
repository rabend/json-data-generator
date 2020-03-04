package com.github.rabend.generators;

import java.util.HashMap;
import java.util.Map;

public class ValueGeneratorsLookup {
    private static Map<String, AbstractValueGenerator> generators = new HashMap<>();

    static {
        generators.put("string", new StringGenerator());
        generators.put("integer", new IntegerGenerator());
        generators.put("number", new DoubleGenerator());
        generators.put("boolean", new BooleanGenerator());
        generators.put("array", new ArrayGenerator());
        generators.put("object", new ObjectGenerator());
    }

    public static AbstractValueGenerator getGeneratorForType(final String typeName) {
        if (generators.containsKey(typeName)) {
            return generators.get(typeName);
        } else {
            throw new NoGeneratorFoundException("No generator for type name '" + typeName + "'");
        }
    }
}
