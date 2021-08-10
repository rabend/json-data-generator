package com.github.rabend.generators

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeOneOf
import io.kotest.matchers.date.shouldNotBeBefore
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldHaveMaxLength
import io.kotest.matchers.string.shouldHaveMinLength
import io.kotest.matchers.string.shouldMatch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.time.LocalDate
import java.time.LocalDateTime

class StringGeneratorSpec: StringSpec({
    val doc = this.javaClass.getResource("/schema/Article.json").readText()
    val propertiesNode = Json.parseToJsonElement(doc).jsonObject["properties"]!! as JsonObject

    "generate random string for simple string property" {
        val nameNode = propertiesNode["name"] as JsonObject
        val randomString = StringGenerator().generateRandomValue(nameNode)

        randomString shouldNotBe JsonNull
        val content = randomString.jsonPrimitive.content
        content shouldHaveMinLength 6
        content shouldHaveMaxLength 50
    }

    "generate string with max length" {
        val countryNode = propertiesNode["country"] as JsonObject

        val randomString = StringGenerator().generateRandomValue(countryNode)

        randomString shouldNotBe JsonNull
        val content = randomString.jsonPrimitive.content
        content shouldHaveMaxLength 2
    }

    "generate string with min length" {
        val productTypeNode = propertiesNode["producttype"] as JsonObject

        val randomString = StringGenerator().generateRandomValue(productTypeNode)

        randomString shouldNotBe JsonNull
        val content = randomString.jsonPrimitive.content
        content shouldHaveMinLength 18
    }

    "generate string with min and max length" {
        val eanNode = propertiesNode["ean"] as JsonObject

        val randomString = StringGenerator().generateRandomValue(eanNode)

        randomString shouldNotBe JsonNull
        val content = randomString.jsonPrimitive.content
        content shouldHaveMinLength 8
        content shouldHaveMaxLength 14
    }

    "generate values for enums" {
        val packagingNode = propertiesNode["packagingtype"] as JsonObject
        val enumValues = packagingNode["enum"] as JsonArray
        val randomString = StringGenerator().generateRandomValue(packagingNode)

        randomString shouldNotBe JsonNull
        val content = randomString.jsonPrimitive.content
        content shouldBeOneOf enumValues.map { it.jsonPrimitive.content }
    }

    "generate values for a pattern" {
        val gtinNode = propertiesNode["gtin"] as JsonObject

        val randomString = StringGenerator().generateRandomValue(gtinNode)

        randomString shouldNotBe JsonNull
        val content = randomString.jsonPrimitive.content
        val pattern = gtinNode["pattern"]!!.jsonPrimitive.content

        content shouldMatch Regex(pattern)
    }

    "generate values for a pattern with min length" {
        val sku1Node = propertiesNode["sku1"] as JsonObject

        val randomString = StringGenerator().generateRandomValue(sku1Node)

        randomString shouldNotBe JsonNull
        val content = randomString.jsonPrimitive.content
        val pattern = sku1Node["pattern"]!!.jsonPrimitive.content

        content shouldMatch Regex(pattern)
        content shouldHaveMinLength 5
    }

    "generate values for a pattern with max length" {
        val sku2Node = propertiesNode["sku2"] as JsonObject

        val randomString = StringGenerator().generateRandomValue(sku2Node)

        randomString shouldNotBe JsonNull
        val content = randomString.jsonPrimitive.content
        val pattern = sku2Node["pattern"]!!.jsonPrimitive.content

        content shouldMatch Regex(pattern)
        content shouldHaveMaxLength 10
    }

    "generate values for a pattern with min and max length" {
        val pluNode = propertiesNode["plu"] as JsonObject

        val randomString = StringGenerator().generateRandomValue(pluNode)

        randomString shouldNotBe JsonNull
        val content = randomString.jsonPrimitive.content
        val pattern = pluNode["pattern"]!!.jsonPrimitive.content

        content shouldMatch Regex(pattern)
        content shouldHaveMinLength 4
        content shouldHaveMaxLength 6
    }

    "generate random Date" {
        val onlineNode = propertiesNode["onlinesince"] as JsonObject

        val randomString = StringGenerator().generateRandomValue(onlineNode)

        randomString shouldNotBe JsonNull
        val content = randomString.jsonPrimitive.content
        val date = LocalDate.parse(content)
        date shouldNotBe null
        date shouldNotBeBefore LocalDate.of(1970, 1, 1)
    }

    "generate random DateTime" {
        val promotionNode = propertiesNode["promotionuntil"] as JsonObject

        val randomString = StringGenerator().generateRandomValue(promotionNode)

        randomString shouldNotBe JsonNull
        val content = randomString.jsonPrimitive.content
        val date = LocalDateTime.parse(content)
        date shouldNotBe null
        date shouldNotBeBefore LocalDateTime.of(1970, 1, 1, 0, 0)
    }
})
