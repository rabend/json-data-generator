package com.github.rabend.generators

import com.fasterxml.jackson.databind.JsonNode
import java.util.stream.Collectors

class ObjectGenerator : AbstractValueGenerator() {
    override fun generateRandomValue(node: JsonNode?): String {
        val propertyToGenerator: MutableMap<String, AbstractValueGenerator> = HashMap()
        return if (node!!.has("properties")) {
            val propertiesNode = node["properties"]
            propertiesNode.fields()
                .forEachRemaining { (key, value): Map.Entry<String, JsonNode> ->
                    propertyToGenerator[key] = ValueGeneratorsLookup.getGeneratorForType(
                        value["type"].asText()
                    )
                }
            propertyToGenerator.entries
                .stream()
                .map { (propName, value): Map.Entry<String, AbstractValueGenerator> ->
                    createKey(propName) + value.generateRandomValue(propertiesNode[propName])
                }
                .collect(Collectors.joining(",", "{", "}"))
        } else {
            "{}"
        }
    }

    private fun createKey(prop: String): String {
        return "\"$prop\":"
    }
}
