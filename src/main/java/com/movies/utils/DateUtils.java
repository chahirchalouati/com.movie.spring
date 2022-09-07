package com.movies.utils;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class DateUtils {
    public static LocalDateTime generateLocalDateTime() {
        return LocalDateTime.of(nextInt(1960, LocalDateTime.now().getYear()), nextInt(1, 12), nextInt(1, 27), nextInt(0, 23), nextInt(0, 59));
    }
}
