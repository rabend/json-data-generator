package com.github.rabend

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.equality.shouldNotBeEqualToComparingFields
import kotlinx.serialization.json.Json

class TestDataGeneratorSpec: StringSpec({
    "generate two different JSON documents with random values" {
        val generator = TestDataGenerator()
        val schemaUrl = this.javaClass.getResource("/schema/Article.json")

        val objectString1 = generator.generateJsonString(schemaUrl)
        val object1 = Json.parseToJsonElement(objectString1)

        val objectString2 = generator.generateJsonString(schemaUrl)
        val object2 = Json.parseToJsonElement(objectString2)

        object1 shouldNotBeEqualToComparingFields object2
    }

})
