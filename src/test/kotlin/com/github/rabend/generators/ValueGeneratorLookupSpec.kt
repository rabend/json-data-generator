package com.github.rabend.generators

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class ValueGeneratorLookupSpec : StringSpec({
    "find a generator by name" {
        ValueGeneratorsLookup.getGeneratorForType("string").shouldBeInstanceOf<StringGenerator>()
        ValueGeneratorsLookup.getGeneratorForType("integer").shouldBeInstanceOf<IntegerGenerator>()
        ValueGeneratorsLookup.getGeneratorForType("number").shouldBeInstanceOf<DoubleGenerator>()
        ValueGeneratorsLookup.getGeneratorForType("boolean").shouldBeInstanceOf<BooleanGenerator>()
        ValueGeneratorsLookup.getGeneratorForType("array").shouldBeInstanceOf<ArrayGenerator>()
        ValueGeneratorsLookup.getGeneratorForType("object").shouldBeInstanceOf<ObjectGenerator>()
    }
})
