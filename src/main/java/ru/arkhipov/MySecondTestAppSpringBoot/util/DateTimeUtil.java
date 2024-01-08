package ru.arkhipov.MySecondTestAppSpringBoot.util;

import java.text.SimpleDateFormat;

public class DateTimeUtil {

    public static SimpleDateFormat getCustonFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    }
}
