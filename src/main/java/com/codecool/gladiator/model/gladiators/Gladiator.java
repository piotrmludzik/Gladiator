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

        currentHp = getMaxAvailableHP();
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

    public int getHp() {
        // TODO
        return 0;
    }

    public int getSp() {
        // TODO
        return 0;
    }

    public int getDex() {
        // TODO
        return 0;
    }

    /**
     * @return a gladiator health points
     */
    public double getCurrentHp() {
        return currentHp;
    }

    /**
     * @param currentHp a gladiator health points
     */
    public void setCurrentHp(double currentHp) {
        this.currentHp = currentHp;
    }

    public void decreaseHpBy() {
        // TODO
    }

    public void healUp() {
        // TODO
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
     * @return maximum available HP value
     */
    public double getMaxAvailableHP() {
        return getMaxAvailablePropertyValue(baseHp, getHpMultiplier().getValue());
    }

    /**
     * @return maximum available SP value
     */
    public double getMaxAvailableSP() {
        return getMaxAvailablePropertyValue(baseSp, getSpMultiplier().getValue());
    }

    /**
     * @return maximum available DEX value
     */
    public double getMaxAvailableDEX() {
        return getMaxAvailablePropertyValue(baseDex, getDexMultiplier().getValue());
    }

    private double getMaxAvailablePropertyValue(int baseValue, double valueMultiplier) {
        return baseValue * valueMultiplier * level;
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
