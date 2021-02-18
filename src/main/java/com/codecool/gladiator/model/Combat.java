package com.codecool.gladiator.model;

import com.codecool.gladiator.model.gladiators.Gladiator;
import com.codecool.gladiator.util.Randomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Combat class, used for simulating fights between pairs of gladiators
 */
public class Combat {

    private Gladiator gladiator1;
    private Gladiator gladiator2;

    private final List<String> combatLog;

    public Combat(Contestants contestants) {
        if (isFiftyFifty()) {
            gladiator1 = contestants.gladiator1;
            gladiator2 = contestants.gladiator2;
        } else {
            gladiator1 = contestants.gladiator2;
            gladiator2 = contestants.gladiator1;
        }

        this.combatLog = new ArrayList<>();
    }

    private boolean isFiftyFifty() {
        return Randomizer.eventWithChance(50);
    }

    /**
     * Simulates the combat and returns the winner.
     * If one of the opponents is null, the winner is the one that is not null
     * If both of the opponents are null, the return value is null
     *
     * @return winner of combat
     */
    public Gladiator simulate() {
        if (isMissingGladiator()) {
            if (isGladiator(gladiator1)) return gladiator1;
            if (isGladiator(gladiator2)) return gladiator2;

            return null;  // both are missing
        }

        do {  // fight!
            if (isChanceOfAttackerHit()) {
                doAttackerHit();
                setLog("hit");
            } else {
                setLog("mishit");
            }

            if (!gladiator2.isDead())
                swapGladiators();
        } while (gladiator2.isDead());

        setLog("win");
        return gladiator1;
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
        double damage  = gladiator1.getSp() * randomStrengthFactor();
        gladiator2.decreaseHp(damage);
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
        String attackerName = gladiator1.getFullName();
        String defenderName = gladiator2.getFullName();

        switch (typeOfLog) {
            case "hit":
                combatLog.add(attackerName + " deals " + defenderName + " damage.");
                break;
            case "mishit":
                combatLog.add(attackerName + " missed.");
                break;
            case "win":
                combatLog.add(defenderName + " has died, " + attackerName + " wins!");
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
