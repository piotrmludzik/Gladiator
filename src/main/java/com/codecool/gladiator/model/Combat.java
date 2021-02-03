package com.codecool.gladiator.model;

import com.codecool.gladiator.model.gladiators.Gladiator;
import com.codecool.gladiator.util.Randomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Combat class, used for simulating fights between pairs of gladiators
 */
public class Combat {

    private Gladiator attacker;
    private Gladiator defender;

    private final List<String> combatLog;

    public Combat(Contestants contestants) {
        if (isFiftyFifty()) {
            attacker = contestants.gladiator1;
            defender = contestants.gladiator2;
        } else {
            attacker = contestants.gladiator2;
            defender = contestants.gladiator1;
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
            if (isGladiator(attacker))
                return attacker;
            if (isGladiator(defender))
                return defender;

            return null;  // both are missing
        }

        while (shouldFightAgain()) {

            swapGladiators();
        }

        return attacker;
    }

    private boolean isMissingGladiator() {
        return !(isGladiator(attacker) && isGladiator(defender));
    }

    private boolean isGladiator(Gladiator gladiator) {
        return gladiator != null;
    }

    private boolean shouldFightAgain() {
        return !(attacker.isDead() || defender.isDead());
    }

    private void swapGladiators() {
        Gladiator gladiatorToSwap = attacker;
        attacker = defender;
        defender = gladiatorToSwap;
    }

    /**
     * @return the first gladiator: attacker
     */
    public Gladiator getAttacker() {
        return attacker;
    }

    /**
     * @return the second gladiator: defender
     */
    public Gladiator getDefender() {
        return defender;
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
