package com.codecool.gladiator.model;

import com.codecool.gladiator.model.gladiators.*;
import com.codecool.gladiator.util.Randomizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class GladiatorFactory {

    private List<String> names;

    public GladiatorFactory(String fileOfNames) {
        try {
            File file = new File(getClass().getClassLoader().getResource(fileOfNames).getFile());
            names = Files.readAllLines(file.toPath());
        } catch (IOException|NullPointerException e) {
            System.out.println("Names file not found or corrupted!");
            System.exit(1);
        }
    }

    /**
     * Instantiates a new gladiator with random name and type.
     * Creating an Archer, an Assassin, or a Brutal has the same chance,
     * while the chance of creating a Swordsman is the double of the chance of creating an Archer.
     * @return new Gladiator
     */
    public Gladiator generateRandomGladiator() {
        String name = getRandomName();
        int hp = getBaseValue();
        int sp = getBaseValue();
        int dex = getBaseValue();
        int lvl = getRandomLevel();

        if (shouldCreateSwordsman())
            return new Swordsman(name, hp, sp, dex, lvl);

        switch (Randomizer.integer(3)) {
            case 0:
                return new Archer(name, hp, sp, dex, lvl);
            case 1:
                return new Assassin(name, hp, sp, dex, lvl);
            case 2:
                return new Brutal(name, hp, sp, dex, lvl);
        }

        throw new RuntimeException("Couldn't create new gladiator!");
    }

    private String getRandomName() {
        return Randomizer.chooseOne(names);
    }

    private int getBaseValue() {
        return Randomizer.integerFromRange(25, 101);
    }

    private int getRandomLevel() {
        return Randomizer.integerFromRange(1, 6);
    }

    private boolean shouldCreateSwordsman() {
        return Randomizer.eventWithChance(50);  // 50% of chance
    }
}
