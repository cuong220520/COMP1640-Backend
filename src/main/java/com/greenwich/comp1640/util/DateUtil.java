package com.greenwich.comp1640.util;

import java.util.Date;

public class DateUtil {
    private DateUtil() {
    }

    public static Date now() {
        return new Date();
    }

    public static Long currentEpochSecond() {
        return new Date().toInstant().getEpochSecond();
    }
}
