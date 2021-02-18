package com.codecool.gladiator.model;

import com.codecool.gladiator.model.gladiators.Gladiator;
import com.codecool.gladiator.util.Randomizer;
import com.codecool.gladiator.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Combat class, used for simulating fights between pairs of gladiators
 */
public class Combat {

    private Gladiator gladiator1;
    private Gladiator gladiator2;

    private final List<String> combatLog = new ArrayList<>();
    double actualDamage = 0;

    public Combat(Contestants contestants) {
        if (isFiftyFifty()) {
            gladiator1 = contestants.gladiator1;
            gladiator2 = contestants.gladiator2;
        } else {
            gladiator1 = contestants.gladiator2;
            gladiator2 = contestants.gladiator1;
        }
    }

    private boolean isFiftyFifty() {
        return Randomizer.eventWithChance(50);
    }

    /**
     * Simulates the combat and returns the winner.
     *
     * @throws IllegalStateException if one of the opponents is null
     */
    public void simulate() {
        if (isMissingGladiator())
            throw new IllegalStateException("One of the gladiators is missing.");

        boolean isDefenderLife;
        do {  // fight!
            if (isChanceOfAttackerHit()) {
                doAttackerHit();
                setLog("hit");
            } else {
                setLog("mishit");
            }

            isDefenderLife = !gladiator2.isDead();
            if (isDefenderLife)
                swapGladiators();
        } while (isDefenderLife);
    }

    private boolean isMissingGladiator() {
        return !(isGladiator(gladiator1) && isGladiator(gladiator2));
    }

    private boolean isGladiator(Gladiator gladiator) {
        return gladiator != null;
    }

    private boolean isChanceOfAttackerHit() {
        return Randomizer.eventWithChance(getChanceOfAttackerHit());
    }

    private int getChanceOfAttackerHit() {
        int dexComparison = (int) Math.round(gladiator1.getDex() - gladiator2.getDex());

        if (dexComparison <= 10) return 10;
        if (dexComparison >= 100) return 100;

        return dexComparison;
    }

    private void doAttackerHit() {
        actualDamage = gladiator1.getSp() * randomStrengthFactor();
        gladiator2.decreaseHp(actualDamage);
    }

    private double randomStrengthFactor() {
        return (double) Randomizer.integerFromRange(1, 6) / 10;
    }

    private void swapGladiators() {
        Gladiator gladiatorToSwap = gladiator1;
        gladiator1 = gladiator2;
        gladiator2 = gladiatorToSwap;
    }

    /**
     * @return the first gladiator: attacker
     */
    public Gladiator getGladiator1() {
        return gladiator1;
    }

    /**
     * @return the second gladiator: defender
     */
    public Gladiator getGladiator2() {
        return gladiator2;
    }

    /**
     * @return the list of two gladiators
     */
    public List<Gladiator> getGladiators() {
        return List.of(gladiator1, gladiator2);
    }

    private void setLog(String typeOfLog) {
        var attackerName = gladiator1.getName();

        switch (typeOfLog) {
            case "hit":
                combatLog.add(String.format("%s deals %s damage",
                        attackerName,
                        Util.formatNumber(actualDamage)));
                break;
            case "mishit":
                combatLog.add(String.format("%s missed", attackerName));
                break;
            default:
                throw new IllegalArgumentException("The typeOfLog argument missing.");
        }
    }

    /**
     * Returns a combat log.
     * @param separator that separate each combat step information
     * @return          a string of combat log
     */
    public String getCombatLog(String separator) {
        return String.join(separator, combatLog);
    }
}
