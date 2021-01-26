package com.codecool.gladiator.model.gladiators;

public enum Multiplier {
    LOW(0.75),
    MEDIUM(1.0),
    HIGH(1.25);

    private final double value;

    Multiplier(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}