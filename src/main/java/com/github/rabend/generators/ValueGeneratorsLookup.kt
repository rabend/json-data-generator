package com.github.rabend.generators

object ValueGeneratorsLookup {
    private val generators: MutableMap<String, AbstractValueGenerator> = HashMap()

    @JvmStatic
    fun getGeneratorForType(typeName: String): AbstractValueGenerator {
        return if (generators.containsKey(typeName)) {
            generators[typeName]!!
        } else {
            throw NoGeneratorFoundException("No generator for type name '$typeName'")
        }
    }

    init {
        generators["string"] = StringGenerator()
        generators["integer"] = IntegerGenerator()
        generators["number"] = DoubleGenerator()
        generators["boolean"] = BooleanGenerator()
        generators["array"] = ArrayGenerator()
        generators["object"] = ObjectGenerator()
    }
}
