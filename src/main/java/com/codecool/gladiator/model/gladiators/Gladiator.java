package com.codecool.gladiator.model.gladiators;

import com.codecool.gladiator.model.gladiators.properties.Multiplier;

public abstract class Gladiator {

    private final String name;
    private final int baseHp;
    private final int baseSp;
    private final int baseDex;
    private double currentHp;
    private int level;

    /**
     * Constructor for Gladiators
     *
     * @param name the gladiator's name
     * @param baseHp the gladiator's base Health Points
     * @param baseSp the gladiator's base Strength Points
     * @param baseDex the gladiator's base Dexterity Points
     * @param level the gladiator's starting Level
     */
    public Gladiator(String name, int baseHp, int baseSp, int baseDex, int level) {
        this.name = name;
        this.baseHp = baseHp;
        this.baseSp = baseSp;
        this.baseDex = baseDex;
        this.level = level;

        currentHp = getHp();
    }

    /**
     * @return Gladiator's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the full name of the gladiator
     * assembled by the subtype and the name
     * (e.g. "Brutal Brutus" or "Archer Leo")
     *
     * @return the full name
     */
    public String getFullName() {
        return String.format("%s %s", this.getClass().getSimpleName(), name);
    }

    /**
     * @return maximum available Helath value
     */
    public double getHp() {
        return getGladiatorPropertyValue(baseHp, getHpMultiplier().getValue());
    }

    /**
     * @return maximum available Strength value
     */
    public double getSp() {
            return getGladiatorPropertyValue(baseSp, getSpMultiplier().getValue());
    }

    /**
     * @return maximum available Dexterity value
     */
    public double getDex() {
        return getGladiatorPropertyValue(baseDex, getDexMultiplier().getValue());
    }

    private double getGladiatorPropertyValue(int baseValue, double multiplier) {
        return baseValue * multiplier * level;
    }

    /**
     * @return a gladiator health points
     */
    public double getCurrentHp() {
        return currentHp;
    }

    /**
     * Reduce gladiator health.
     * @param damage value to reduce current health
     */
    public void decreaseHp(double damage) {
        currentHp = currentHp - damage;
    }

    /**
     * Heal up the gladiator to maximum available health.
     */
    public void healUp() {
        currentHp = getHp();
    }

    /**
     * @return  gladiator level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Increase a gladiator level
     */
    public void levelUp() {
        level++;
    }

    /**
     * @return true if a gladiator should die,
     *         otherwise false
     */
    public boolean isDead() {
        return currentHp <= 0;
    }

    /**
     * @return HP multiplier of the gladiator subclass
     */
    protected abstract Multiplier getHpMultiplier();

    /**
     * @return SP multiplier of the gladiator subclass
     */
    protected abstract Multiplier getSpMultiplier();

    /**
     * @return DEX multiplier of the gladiator subclass
     */
    protected abstract Multiplier getDexMultiplier();
}
