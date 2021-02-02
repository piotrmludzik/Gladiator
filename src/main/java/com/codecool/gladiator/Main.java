package com.codecool.gladiator;

import com.codecool.gladiator.model.gladiators.*;

public class Main {

    public static void main(String[] args) {
        Gladiator glad = new Brutal("Alex", 10, 10, 10, 1);
        System.out.println(glad.getName());
        System.out.println(glad.getFullName());
//        Colosseum colosseum = new Colosseum(new ConsoleView(), new GladiatorFactory("Names.txt"));
//        colosseum.welcomeAndAskForStages();
//        colosseum.runSimulation();
    }

}
