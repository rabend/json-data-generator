package com.github.rabend.generators

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.date.shouldNotBeAfter
import io.kotest.matchers.date.shouldNotBeBefore
import java.time.LocalDate
import java.time.LocalDateTime

class SimpleDateGeneratorSpec: StringSpec({
    "generate random Date" {
        val start = LocalDate.of(1970, 1, 1)
        val currentYear = LocalDate.now().year
        val end = LocalDate.of(currentYear, 12, 31)

        val randomDate = SimpleDateGenerator().generateDate()

        randomDate shouldNotBeBefore start
        randomDate shouldNotBeAfter end
    }

    "generate random DateTime" {
        val start = LocalDateTime.of(1970, 1, 1, 0, 0, 0)
        val currentYear = LocalDate.now().year
        val end = LocalDateTime.of(currentYear, 12, 31, 23, 59, 59)

        val randomDateTime = SimpleDateGenerator().generateDateTime()

        randomDateTime shouldNotBeBefore start
        randomDateTime shouldNotBeAfter end
    }
})
