package com.github.rabend.generators;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleDateGenerator {
    public LocalDate generateDate() {
        DateSeed dateSeed = new DateSeed().invoke();
        int year = dateSeed.getYear();
        int month = dateSeed.getMonth();
        int day = dateSeed.getDay();

        return LocalDate.of(year, month, day);
    }

    public LocalDateTime generateDateTime() {
        DateSeed dateSeed = new DateSeed().invoke();
        int year = dateSeed.getYear();
        int month = dateSeed.getMonth();
        int day = dateSeed.getDay();
        int hour = dateSeed.getHour();
        int minute = dateSeed.getMinute();
        int second = dateSeed.getSecond();

        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    private class DateSeed {
        private int year;
        private int month;
        private int day;
        private int hour;
        private int minute;
        private int second;

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        public int getHour() {
            return hour;
        }

        public int getMinute() {
            return minute;
        }

        public int getSecond() {
            return second;
        }

        public DateSeed invoke() {
            ThreadLocalRandom random = ThreadLocalRandom.current();

            int currentYear = LocalDate.now().getYear();
            year = random.nextInt(1970, currentYear);
            month = random.nextInt(1, 13);
            if (month == 2) {
                day = random.nextInt(1, 29);
            } else if (month == 9 || month == 11 || month % 2 == 0) {
                day = random.nextInt(1, 31);
            } else {
                day = random.nextInt(1, 32);
            }

            hour = random.nextInt(0, 24);
            minute = random.nextInt(0, 60);
            second = random.nextInt(0, 60);

            return this;
        }
    }
}
