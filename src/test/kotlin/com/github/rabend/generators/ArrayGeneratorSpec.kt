package com.github.rabend.generators

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAnyOf
import io.kotest.matchers.shouldBe
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class ArrayGeneratorSpec: StringSpec({
    val doc = this.javaClass.getResource("/schema/Article.json").readText()
    val propertiesNode = Json.parseToJsonElement(doc).jsonObject["properties"]!! as JsonObject

    "generate simple array of random strings" {
        val productTagsNode = propertiesNode["producttags"] as JsonObject
        val randomArray = ArrayGenerator().generateRandomValue(productTagsNode).jsonArray

        randomArray.all { it.jsonPrimitive.isString } shouldBe true
    }

    "generate array backed by an enum" {
        val flagsNode = propertiesNode["flags"] as JsonObject
        val validFlags = listOf("org", "foo", "bar", "baz");
        val randomArray = ArrayGenerator().generateRandomValue(flagsNode).jsonArray

        randomArray.map { it.jsonPrimitive.content } shouldContainAnyOf validFlags
        randomArray.map { it.jsonPrimitive.content }
            .dropWhile { validFlags.contains(it) }.size shouldBe 0
    }
})
