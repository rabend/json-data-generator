package com.github.rabend.generators

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.util.concurrent.ThreadLocalRandom

class BooleanGenerator : AbstractValueGenerator() {
    override fun generateRandomValue(node: JsonObject): JsonElement {
        val random = ThreadLocalRandom.current()
        val randomInt = random.nextInt(0, 2)
        return JsonPrimitive(randomInt == 0)
    }
}
