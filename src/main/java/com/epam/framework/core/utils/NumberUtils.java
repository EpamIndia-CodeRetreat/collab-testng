package com.epam.framework.core.utils;

import com.google.common.primitives.Ints;

import java.util.Optional;

public class NumberUtils {

    public static boolean isNumber(String number) {
        return org.apache.commons.lang3.math.NumberUtils.isDigits(number);
    }

    public static int min(int[] array) {
        return org.apache.commons.lang3.math.NumberUtils.min(array);
    }

    public static int max(int[] array) {
        return org.apache.commons.lang3.math.NumberUtils.max(array);
    }

    public static int generateRandomNumber(final int min, final int max) {
        return (int)(Math.random() * max + min);
    }

    public static int getInt(String value) {
        int number = Optional.ofNullable(value)
                .map(Ints::tryParse)
                .orElse(0);
        return number;
    }


}
