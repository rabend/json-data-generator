package com.github.rabend

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.equality.shouldNotBeEqualToComparingFields
import io.kotest.matchers.types.shouldBeTypeOf
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray

class TestDataGeneratorSpec: StringSpec({
    "generate two different JSON documents with random objects" {
        val generator = TestDataGenerator()
        val schemaUrl = this.javaClass.getResource("/schema/Article.json")

        val objectString1 = generator.generateJsonString(schemaUrl)
        val object1 = Json.parseToJsonElement(objectString1)

        val objectString2 = generator.generateJsonString(schemaUrl)
        val object2 = Json.parseToJsonElement(objectString2)

        object1 shouldNotBeEqualToComparingFields object2
    }

    "generate two different JSON documents with random arrays" {
        val generator = TestDataGenerator()
        val schemaUrl = this.javaClass.getResource("/schema/Array.json")

        val arrayString1 = generator.generateJsonString(schemaUrl)
        val array1 = Json.parseToJsonElement(arrayString1)

        val arrayString2 = generator.generateJsonString(schemaUrl)
        val array2 = Json.parseToJsonElement(arrayString2)

        array1.shouldBeTypeOf<JsonArray>()
        array2.shouldBeTypeOf<JsonArray>()
        array1 shouldNotBeEqualToComparingFields array2
    }

})
