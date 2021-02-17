package com.codecool.gladiator.model;

import com.codecool.gladiator.model.gladiators.Gladiator;

public class Contestants {

    public final Gladiator gladiator1;
    public final Gladiator gladiator2;

    public Contestants(Gladiator gladiator1, Gladiator gladiator2) {
        this.gladiator1 = gladiator1;
        this.gladiator2 = gladiator2;
    }

    @Override
    public String toString() {
        return gladiator1 + " vs " + gladiator2;
    }
}
