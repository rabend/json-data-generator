package com.github.rabend.generators

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive
import java.util.concurrent.ThreadLocalRandom

class IntegerGenerator : AbstractValueGenerator() {
    override fun generateRandomValue(node: JsonObject): JsonElement{
        val random = ThreadLocalRandom.current()
        val randomInt = if (node.containsKey("minimum")) {
            random.nextInt(node["minimum"]!!.jsonPrimitive.int, Int.MAX_VALUE)
        } else {
            random.nextInt(0, Int.MAX_VALUE)
        }
        return JsonPrimitive(randomInt)
    }
}
