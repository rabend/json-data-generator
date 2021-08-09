package com.github.rabend.generators

import com.fasterxml.jackson.databind.JsonNode
import java.util.concurrent.ThreadLocalRandom

class BooleanGenerator : AbstractValueGenerator() {
    override fun generateRandomValue(node: JsonNode?): String? {
        val random = ThreadLocalRandom.current()
        val randomInt = random.nextInt(0, 2)
        return (randomInt == 0).toString()
    }
}
