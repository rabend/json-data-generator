package com.github.rabend.generators

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

interface AbstractValueGenerator {
    fun generateRandomValue(baseObject: JsonObject): JsonElement
}
