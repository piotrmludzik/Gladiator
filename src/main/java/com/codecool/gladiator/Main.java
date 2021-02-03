package com.codecool.gladiator;

import com.codecool.gladiator.model.GladiatorFactory;
import com.codecool.gladiator.model.gladiators.*;

public class Main {

    public static void main(String[] args) {
        GladiatorFactory gf = new GladiatorFactory("names.txt");
        Gladiator newGladiator = gf.generateRandomGladiator();
        System.out.println(newGladiator.getFullName());
//        Colosseum colosseum = new Colosseum(new ConsoleView(), new GladiatorFactory("names.txt"));
//        colosseum.welcomeAndAskForStages();
//        colosseum.runSimulation();
    }

}
