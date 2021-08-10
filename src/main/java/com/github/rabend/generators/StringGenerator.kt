package com.github.rabend.generators

import com.mifmif.common.regex.Generex
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class StringGenerator: AbstractValueGenerator {
    override fun generateRandomValue(baseObject: JsonObject): JsonElement {
        if (baseObject.containsKey(FORMAT)) {
            val format = baseObject[FORMAT]!!.jsonPrimitive.content
            if (format.equals("date", ignoreCase = true)) {
                return JsonPrimitive(SimpleDateGenerator().generateDate().toString())
            } else if (format.equals("date-time", ignoreCase = true)) {
                return JsonPrimitive(SimpleDateGenerator().generateDateTime().toString())
            }
        }
        var maxLength = 50
        var minLength = 6
        if (baseObject.containsKey("maxLength")) {
            maxLength = baseObject["maxLength"]!!.jsonPrimitive.int + 1
            minLength = 0
        }
        if (baseObject.containsKey("minLength")) {
            minLength = baseObject["minLength"]!!.jsonPrimitive.int
        }
        if (baseObject.containsKey("pattern")) {
            var regex = baseObject["pattern"]!!.jsonPrimitive.content
            regex = regex.replace("^", "")
            regex = regex.replace("$", "")
            val generex = Generex(regex)
            return JsonPrimitive(generex.random(minLength, maxLength - 1))
        }
        val random = ThreadLocalRandom.current()
        if (baseObject.containsKey("enum")) {
            val enumNode = baseObject["enum"]!!.jsonArray
            val upperBound = if (enumNode.size == 1) 1 else enumNode.size - 1
            val randomIndex = random.nextInt(upperBound)
            return JsonPrimitive(enumNode[randomIndex].jsonPrimitive.content)
        }
        val randomLength = random.nextInt(minLength, maxLength)
        val chars = CharArray(randomLength)
        for (i in 0 until randomLength) {
            val bound = random.nextInt(ALL_CHARS.length - 1)
            chars[i] = ALL_CHARS.toCharArray()[bound]
        }

        return JsonPrimitive(String(chars))
    }

    companion object {
        private const val UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ "
        private val LOWERCASE = UPPERCASE.lowercase(Locale.getDefault())
        private const val DIGITS = "0123456789"
        private val ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS
        const val FORMAT = "format"
    }
}
