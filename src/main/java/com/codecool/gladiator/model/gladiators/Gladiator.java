package com.codecool.gladiator.model.gladiators;

public abstract class Gladiator {

    private final String name;
    private final int baseHp;
    private final int baseSp;
    private final int baseDex;
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
