package com.codecool.gladiator.model.gladiators;

public class Brutal extends Gladiator {

    private static final Multiplier HP_MULTIPLIER = Multiplier.HIGH;
    private static final Multiplier SP_MULTIPLIER = Multiplier.HIGH;
    private static final Multiplier DEX_MULTIPLIER = Multiplier.LOW;

    public Brutal(String name, int baseHp, int baseSp, int baseDex, int level) {
        super(name, baseHp, baseSp, baseDex, level);
    }

    @Override
    protected Multiplier getHpMultiplier() {
        return HP_MULTIPLIER;
    }

    @Override
    protected Multiplier getSpMultiplier() {
        return SP_MULTIPLIER;
    }

    @Override
    protected Multiplier getDexMultiplier() {
        return DEX_MULTIPLIER;
    }
}
