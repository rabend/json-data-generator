package com.github.rabend

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.rabend.generators.ObjectGenerator
import java.io.IOException
import java.net.URL

class TestDataGenerator(private val schemaUrl: URL) {
    private val mapper: ObjectMapper

    /**
     * Generates a json string with random values for the keys defined in the json schema.
     * Values will be generated according to any restrictions in the schema, if present.
     *
     * @return a json string
     * @throws IOException in case the schema cannot be read
     */
    @Throws(IOException::class)
    fun generateJsonString(): String {
        val baseNode = mapper.readTree(schemaUrl)
        return ObjectGenerator().generateRandomValue(baseNode)
    }

    /**
     * Constructor
     *
     * @param schemaUrl URL to a valid json schema
     */
    init {
        mapper = ObjectMapper()
    }
}
