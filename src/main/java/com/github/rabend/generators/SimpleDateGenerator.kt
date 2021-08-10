package com.github.rabend.generators

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.concurrent.ThreadLocalRandom

class SimpleDateGenerator {
    fun generateDate(): LocalDate {
        val dateSeed = DateSeed().invoke()
        val year = dateSeed.year
        val month = dateSeed.month
        val day = dateSeed.day
        return LocalDate.of(year, month, day)
    }

    fun generateDateTime(): LocalDateTime {
        val dateSeed = DateSeed().invoke()
        val year = dateSeed.year
        val month = dateSeed.month
        val day = dateSeed.day
        val hour = dateSeed.hour
        val minute = dateSeed.minute
        val second = dateSeed.second
        return LocalDateTime.of(year, month, day, hour, minute, second)
    }

    private inner class DateSeed {
        var year = 0
            private set
        var month = 0
            private set
        var day = 0
            private set
        var hour = 0
            private set
        var minute = 0
            private set
        var second = 0
            private set

        operator fun invoke(): DateSeed {
            val random = ThreadLocalRandom.current()
            val currentYear = LocalDate.now().year
            year = random.nextInt(1970, currentYear)
            month = random.nextInt(1, 13)
            day = if (month == 2) {
                random.nextInt(1, 29)
            } else if (month == 9 || month == 11 || month % 2 == 0) {
                random.nextInt(1, 31)
            } else {
                random.nextInt(1, 32)
            }
            hour = random.nextInt(0, 24)
            minute = random.nextInt(0, 60)
            second = random.nextInt(0, 60)
            return this
        }
    }
}
