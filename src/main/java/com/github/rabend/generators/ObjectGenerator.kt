package com.github.rabend.generators

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class ObjectGenerator : AbstractValueGenerator() {
    override fun generateRandomValue(baseObject: JsonObject): JsonElement {
        val propertyToGenerator: MutableMap<String, AbstractValueGenerator> = HashMap()
        return if (baseObject.containsKey("properties")) {
            val propertiesNode = baseObject["properties"] as JsonObject
            propertiesNode.toList()
                .forEach {
                    propertyToGenerator[it.first] = ValueGeneratorsLookup.getGeneratorForType(
                        (it.second as JsonObject)["type"]!!.jsonPrimitive.content
                    )
                }
            val randomValues = propertyToGenerator.entries
                .map { (propName, value): Map.Entry<String, AbstractValueGenerator> ->
                    propName to value.generateRandomValue(propertiesNode[propName] as JsonObject)
                }
            Json.encodeToString(randomValues)
            JsonObject(randomValues.toMap())
        } else {
            JsonObject(emptyMap())
        }
    }
}
