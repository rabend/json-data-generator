package com.github.rabend.generators

import com.fasterxml.jackson.databind.JsonNode

abstract class AbstractValueGenerator {
    abstract fun generateRandomValue(node: JsonNode?): String?
}
