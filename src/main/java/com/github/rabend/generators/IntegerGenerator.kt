package com.github.rabend.generators

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive
import java.util.concurrent.ThreadLocalRandom

class IntegerGenerator: AbstractValueGenerator {
    override fun generateRandomValue(baseObject: JsonObject): JsonElement{
        val random = ThreadLocalRandom.current()
        val randomInt = if (baseObject.containsKey("minimum")) {
            random.nextInt(baseObject["minimum"]!!.jsonPrimitive.int, Int.MAX_VALUE)
        } else {
            random.nextInt(0, Int.MAX_VALUE)
        }
        return JsonPrimitive(randomInt)
    }
}
