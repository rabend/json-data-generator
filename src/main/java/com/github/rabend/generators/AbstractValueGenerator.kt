package com.github.rabend.generators

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

abstract class AbstractValueGenerator {
    abstract fun generateRandomValue(node: JsonObject): JsonElement
}
