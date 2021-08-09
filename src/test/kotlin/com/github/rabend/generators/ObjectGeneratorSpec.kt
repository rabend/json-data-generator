package com.github.rabend.generators

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class ObjectGeneratorSpec: StringSpec({
    val doc = this.javaClass.getResource("/schema/Article.json").readText()
    val propertiesNode = Json.parseToJsonElement(doc).jsonObject["properties"]!! as JsonObject

    "generate an object according to a schema" {
        val itemsNode = propertiesNode["eans"]!!.jsonObject["items"]!! as JsonObject
        val randomObject = ObjectGenerator().generateRandomValue(itemsNode).jsonObject

        randomObject.containsKey("ean") shouldBe true
        randomObject.containsKey("type") shouldBe true
        randomObject["ean"]!!.jsonPrimitive.isString shouldBe true
        randomObject["type"]!!.jsonPrimitive.double shouldBeGreaterThan 0.0
    }

})
