package com.github.rabend.generators;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class SimpleDateGeneratorTest {
    @Test
    public void shouldGenerateRandomDate() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        int currentYear = LocalDate.now().getYear();
        LocalDate end = LocalDate.of(currentYear, 12, 31);

        LocalDate randomDate = new SimpleDateGenerator().generateDate();
        assertTrue(randomDate.isAfter(start));
        assertTrue(randomDate.isBefore(end));
    }

    @Test
    public void shouldGenerateRandomDateTime() {
        LocalDateTime start = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        int currentYear = LocalDate.now().getYear();
        LocalDateTime end = LocalDateTime.of(currentYear, 12, 31, 23, 59, 59);

        LocalDateTime randomDateTime = new SimpleDateGenerator().generateDateTime();
        assertTrue(randomDateTime.isAfter(start));
        assertTrue(randomDateTime.isBefore(end));
    }
}
