package com.github.rabend.generators

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class IntegerGeneratorSpec: StringSpec({
    val doc = this.javaClass.getResource("/schema/Article.json").readText()
    val propertiesNode = Json.parseToJsonElement(doc).jsonObject["properties"]!! as JsonObject

    "generate random integer greater than 0" {
        val weightNode = propertiesNode["weight"] as JsonObject
        val randomInt = IntegerGenerator().generateRandomValue(weightNode).jsonPrimitive.int

        randomInt shouldBeGreaterThanOrEqual 0
        randomInt shouldBeLessThanOrEqual Int.MAX_VALUE
    }

    "generate random integer with min value" {
        val versionNode = propertiesNode["version"] as JsonObject
        val randomInt = IntegerGenerator().generateRandomValue(versionNode).jsonPrimitive.int

        randomInt shouldBeGreaterThanOrEqual 200
        randomInt shouldBeLessThanOrEqual Int.MAX_VALUE
    }
})
