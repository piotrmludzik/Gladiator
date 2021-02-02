package com.codecool.gladiator.util;

import java.util.Random;

public class Randomizer {

    private static final Random RANDOM = new Random();

    /**
     * Generate a random integer from a given range.
     * @param lower lower inclusive limit of the range
     * @param upper upper exclusive limit of the range
     * @return      a random number between a lower and an upper bound
     */
    public static int integerFromRange(int lower, int upper) {
        return lower + RANDOM.nextInt(upper - lower);
    }

    /**
     * Choose a random element from an array of strings.
     * @param possibilities array of strings to choose from
     * @return              the chosen string
     */
    public static String chooseOne(String[] possibilities) {
        if (possibilities == null || possibilities.length < 1) {
            throw new IllegalArgumentException("Possibilities should be a non-empty array of strings.");
        }

        return possibilities[RANDOM.nextInt(possibilities.length)];
    }

    /**
     * Determine whether an event has occured given the chance for it.
     * @param chance the chance of the event happening in percents.
     * @return       true if the event has happened.
     */
    public static boolean eventWithChance(int chance) {
        return RANDOM.nextInt(100) < chance;
    }

    private Randomizer() {
        throw new AssertionError("The Randomizer class cannot be an object.");
    }
}
