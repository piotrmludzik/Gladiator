package com.codecool.gladiator.util;

import java.util.List;
import java.util.Random;

public class Randomizer {

    private static final Random RANDOM = new Random();

    /**
     * Generate a random integer from a 0 (inclusive) to a given upper limit.
     * @param upper upper exclusive limit of the range
     * @return      a random number between a 0 and an upper bound
     */
    public static int integer(int upper) {
        return RANDOM.nextInt(upper);
    }

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
     * Determine whether an event has occurred given the chance for it.
     * @param chance the chance of the event happening in percents (0-100).
     * @return       true if the event has happened.
     */
    public static boolean eventWithChance(int chance) {
        if (chance < 0 || chance > 100)
            throw new IllegalArgumentException("A chance value should be in a range from 0 to 100 percent.");

        return RANDOM.nextInt(101) < chance;
    }

    private Randomizer() {
        throw new AssertionError("The Randomizer class cannot be an object.");
    }
}
