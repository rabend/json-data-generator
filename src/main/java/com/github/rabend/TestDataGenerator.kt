package com.github.rabend

import com.github.rabend.generators.ArrayGenerator
import com.github.rabend.generators.ObjectGenerator
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.IOException
import java.net.URL

class TestDataGenerator {

    /**
     * Generates a json string with random values for the keys defined in the json schema.
     * Values will be generated according to any restrictions in the schema, if present.
     *
     * @return a json string
     * @throws IOException in case the schema cannot be read
     */
    @Throws(IOException::class)
    fun generateJsonString(schemaUrl: URL): String {
        val schemaString = schemaUrl.readText()
        val baseObject = Json.parseToJsonElement(schemaString) as JsonObject

        return when(baseObject["type"]?.jsonPrimitive?.content) {
            "object" -> ObjectGenerator().generateRandomValue(baseObject).toString()
            "array" -> ArrayGenerator().generateRandomValue(baseObject).toString()
            else -> throw InvalidTypeException("Neither 'object' nor 'array' was given as top level type!")
        }
    }
}
