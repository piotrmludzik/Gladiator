package com.codecool.gladiator.util;

import java.util.List;
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
     * Choose a random element from a list of strings.
     * @param possibilities List of strings to choose from
     * @return              the chosen string
     */
    public static String chooseOne(List<String> possibilities) {
        if (possibilities == null || possibilities.isEmpty())
            throw new IllegalArgumentException("Possibilities should be a non-empty list of strings.");

        return possibilities.get(RANDOM.nextInt(possibilities.size()));
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
