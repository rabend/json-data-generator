package com.github.rabend.generators

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class ObjectGenerator: AbstractValueGenerator {
    override fun generateRandomValue(baseObject: JsonObject): JsonElement {
        return if (baseObject.containsKey("properties")) {
            val propertiesNode = baseObject["properties"] as JsonObject
            val propertyToGenerator = propertiesNode.toList().associate {
                it.first to ValueGeneratorsLookup.getGeneratorForType(
                    (it.second as JsonObject)["type"]!!.jsonPrimitive.content
                )
            }
            val randomValuesMap =
                propertyToGenerator.entries.associate { (propName, value): Map.Entry<String, AbstractValueGenerator> ->
                    propName to value.generateRandomValue(propertiesNode[propName] as JsonObject)
                }
            JsonObject(randomValuesMap)
        } else {
            JsonObject(emptyMap())
        }
    }
}
