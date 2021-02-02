package com.codecool.gladiator.model.gladiators;

import com.codecool.gladiator.model.gladiators.properties.Multiplier;

public class Archer extends Gladiator {

    private static final Multiplier HP_MULTIPLIER = Multiplier.MEDIUM;
    private static final Multiplier SP_MULTIPLIER = Multiplier.MEDIUM;
    private static final Multiplier DEX_MULTIPLIER = Multiplier.HIGH;

    public Archer(String name, int baseHp, int baseSp, int baseDex, int level) {
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
