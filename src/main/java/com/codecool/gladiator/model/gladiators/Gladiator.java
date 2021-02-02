package com.codecool.gladiator.model.gladiators;

import com.codecool.gladiator.model.gladiators.properties.Multiplier;

public abstract class Gladiator {

    private final String name;
    private int level;
    private double health;
    private final int baseHp;
    private final int baseSp;
    private final int baseDex;

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
        this.level = level;
        this.baseHp = baseHp;
        this.baseSp = baseSp;
        this.baseDex = baseDex;

        health = getMaxAvalibleHP();
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
     * @return a gladiator health points
     */
    public double getHealth() {
        return health;
    }

    /**
     * @param health a gladiator health points
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * @return maximum avalible HP value
     */
    public double getMaxAvalibleHP() {
        return getMaxAvaliblePropertyValue(baseHp, getHpMultiplier().getValue());
    }

    /**
     * @return maximum avalible SP value
     */
    public double getMaxAvalibleSP() {
        return getMaxAvaliblePropertyValue(baseSp, getSpMultiplier().getValue());
    }

    /**
     * @return maximum avalible DEX value
     */
    public double getMaxAvalibleDEX() {
        return getMaxAvaliblePropertyValue(baseDex, getDexMultiplier().getValue());
    }

    private double getMaxAvaliblePropertyValue(int baseValue, double valueMultiplier) {
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

    /**
     * @return true if a gladiator should die,
     *         otherwise false
     */
    public boolean shoudDie() {
        return health <= 0;
    }
}
