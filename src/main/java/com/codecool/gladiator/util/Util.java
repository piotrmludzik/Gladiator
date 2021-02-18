package com.codecool.gladiator.util;

public class Util {

    /**
     * Returns a formatted string from given a numeric value.
     * @param value  value to format
     * @return       string represents formatted value
     */
    public static String formatNumber(double value) {
        return String.format("%,.0f", value);
    }

    private Util() {
        throw new AssertionError("The Util class cannot be an object.");
    }
}
