package com.github.rabend.generators

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import java.util.concurrent.ThreadLocalRandom

class ArrayGenerator : AbstractValueGenerator() {
    override fun generateRandomValue(node: JsonObject): JsonElement {
        val itemsNode = node["items"] as JsonObject
        val itemGenerator = ValueGeneratorsLookup.getGeneratorForType(itemsNode["type"]!!.jsonPrimitive.content)
        var arrayCount = ThreadLocalRandom.current().nextInt(6)
        if (itemsNode.containsKey("enum")) {
            arrayCount = itemsNode["enum"]!!.jsonArray.size
        }
        val values: MutableSet<JsonElement> = HashSet()
        for (i in 0 until arrayCount) {
            values.add(itemGenerator.generateRandomValue(itemsNode))
        }
        return JsonArray(values.toList())
    }
}
