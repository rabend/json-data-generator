package com.github.rabend.generators

import com.fasterxml.jackson.databind.JsonNode
import java.util.concurrent.ThreadLocalRandom
import java.util.stream.Collectors

class ArrayGenerator : AbstractValueGenerator() {
    override fun generateRandomValue(node: JsonNode?): String? {
        val itemsNode = node!!["items"]
        val itemGenerator = ValueGeneratorsLookup.getGeneratorForType(itemsNode["type"].asText())
        var arrayCount = ThreadLocalRandom.current().nextInt(6)
        if (itemsNode.has("enum")) {
            arrayCount = itemsNode.withArray<JsonNode>("enum").size()
        }
        val values: MutableSet<String?> = HashSet()
        for (i in 0 until arrayCount) {
            values.add(itemGenerator.generateRandomValue(itemsNode))
        }
        return values.stream().collect(Collectors.joining(",", "[", "]"))
    }
}
