package com.codecool.gladiator;

import com.codecool.gladiator.controller.Colosseum;
import com.codecool.gladiator.model.GladiatorFactory;
import com.codecool.gladiator.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        Colosseum colosseum = new Colosseum(new ConsoleView(), new GladiatorFactory("names.txt"));
        colosseum.welcomeAndAskForStages();
        colosseum.runSimulation();
    }

}
