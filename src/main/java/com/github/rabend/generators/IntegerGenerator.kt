package com.github.rabend.generators

import com.fasterxml.jackson.databind.JsonNode
import java.util.concurrent.ThreadLocalRandom

class IntegerGenerator : AbstractValueGenerator() {
    override fun generateRandomValue(node: JsonNode?): String? {
        val randomInt: Int
        val random = ThreadLocalRandom.current()
        if (node!!.has("minimum")) {
            randomInt = random.nextInt(node["minimum"].asInt(), Int.MAX_VALUE)
            return randomInt.toString()
        }
        randomInt = random.nextInt(0, Int.MAX_VALUE)
        return randomInt.toString()
    }
}
