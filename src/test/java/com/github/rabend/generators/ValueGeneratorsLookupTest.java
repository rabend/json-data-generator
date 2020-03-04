package com.github.rabend.generators;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ValueGeneratorsLookupTest {
    @Test
    public void shouldFindAGeneratorByTypeName() {
        AbstractValueGenerator stringGen = ValueGeneratorsLookup.getGeneratorForType("string");
        assertNotNull(stringGen);
        assertEquals(StringGenerator.class, stringGen.getClass());
    }
}
