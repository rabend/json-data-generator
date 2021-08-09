package com.github.rabend.generators

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.shouldBeGreaterThan
import io.kotest.matchers.doubles.shouldBeGreaterThanOrEqual
import io.kotest.matchers.doubles.shouldBeLessThanOrEqual
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class DoubleGeneratorSpec: StringSpec({
    val doc = this.javaClass.getResource("/schema/Article.json").readText()
    val propertiesNode = Json.parseToJsonElement(doc).jsonObject["properties"]!! as JsonObject

    "generate random double with value greater 0" {
        val heightNode = propertiesNode["height"] as JsonObject
        val randomDouble = DoubleGenerator().generateRandomValue(heightNode).jsonPrimitive.double

        randomDouble shouldBeGreaterThanOrEqual 0.0
    }

    "generate random double with exclusive minimum value" {
        val unitsNode = propertiesNode["units"] as JsonObject
        val randomDouble = DoubleGenerator().generateRandomValue(unitsNode).jsonPrimitive.double

        randomDouble shouldBeGreaterThan 0.0
    }

    "generate random double with maximum value" {
        val fitRatioNode = propertiesNode["fitratio"] as JsonObject
        val randomDouble = DoubleGenerator().generateRandomValue(fitRatioNode).jsonPrimitive.double

        randomDouble shouldBeGreaterThanOrEqual 0.0
        randomDouble shouldBeLessThanOrEqual  1.0
    }
})
