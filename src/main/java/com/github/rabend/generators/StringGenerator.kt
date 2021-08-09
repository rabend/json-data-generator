package com.github.rabend.generators

import com.fasterxml.jackson.databind.JsonNode
import com.mifmif.common.regex.Generex
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class StringGenerator : AbstractValueGenerator() {
    override fun generateRandomValue(node: JsonNode?): String? {
        if (node!!.has(FORMAT)) {
            if (node[FORMAT].asText().equals("date", ignoreCase = true)) {
                return "\"" + SimpleDateGenerator().generateDate().toString() + "\""
            } else if (node[FORMAT].asText().equals("date-time", ignoreCase = true)) {
                return "\"" + SimpleDateGenerator().generateDateTime().toString() + "\""
            }
        }
        var maxLength = 50
        var minLength = 6
        if (node.has("maxLength")) {
            maxLength = node["maxLength"].asInt() + 1
            minLength = 0
        }
        if (node.has("minLength")) {
            minLength = node["minLength"].asInt()
        }
        if (node.has("pattern")) {
            var regex = node["pattern"].asText()
            regex = regex.replace("^", "")
            regex = regex.replace("$", "")
            val generex = Generex(regex)
            return "\"" + generex.random(minLength, maxLength - 1) + "\""
        }
        val random = ThreadLocalRandom.current()
        if (node.has("enum")) {
            val enumNode = node.withArray<JsonNode>("enum")
            val bound = if (enumNode.size() == 1) 1 else enumNode.size() - 1
            val randomIndex = random.nextInt(bound)
            return "\"" + enumNode[randomIndex].asText() + "\""
        }
        val randomLength = random.nextInt(minLength, maxLength)
        val chars = CharArray(randomLength)
        for (i in 0 until randomLength) {
            val bound = random.nextInt(ALL_CHARS.length - 1)
            chars[i] = ALL_CHARS.toCharArray()[bound]
        }
        return "\"" + String(chars) + "\""
    }

    companion object {
        private const val UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ "
        private val LOWERCASE = UPPERCASE.lowercase(Locale.getDefault())
        private const val DIGITS = "0123456789"
        private val ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS
        const val FORMAT = "format"
    }
}
