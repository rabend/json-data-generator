package com.github.rabend.generators

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonPrimitive
import java.util.concurrent.ThreadLocalRandom

class DoubleGenerator : AbstractValueGenerator() {
    override fun generateRandomValue(node: JsonObject): JsonElement {
        val random = ThreadLocalRandom.current()
        var minVal = 0.0
        var maxVal = Double.MAX_VALUE
        var exclusive = false
        if (node.containsKey("minimum")) {
            if (node.containsKey("exclusiveMinimum")) {
                exclusive = node["exclusiveMinimum"]!!.jsonPrimitive.boolean
            }
            minVal = node["minimum"]!!.jsonPrimitive.double
            if (exclusive) {
                minVal++
            }
        }
        if (node.containsKey("maximum")) {
            maxVal = node["maximum"]!!.jsonPrimitive.double
        }
        val randomDouble = random.nextDouble(minVal, maxVal)
        return JsonPrimitive(randomDouble)
    }
}
