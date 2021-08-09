package com.github.rabend.generators

import com.fasterxml.jackson.databind.JsonNode
import java.util.concurrent.ThreadLocalRandom

class DoubleGenerator : AbstractValueGenerator() {
    override fun generateRandomValue(node: JsonNode?): String? {
        val random = ThreadLocalRandom.current()
        var minVal = 0.0
        var maxVal = Double.MAX_VALUE
        var exclusive = false
        if (node!!.has("minimum")) {
            if (node.has("exclusiveMinimum")) {
                exclusive = node["exclusiveMinimum"].asBoolean()
            }
            minVal = node["minimum"].asDouble()
            if (exclusive) {
                minVal++
            }
        }
        if (node.has("maximum")) {
            maxVal = node["maximum"].asDouble()
        }
        val randomDouble = random.nextDouble(minVal, maxVal)
        return randomDouble.toString()
    }
}
