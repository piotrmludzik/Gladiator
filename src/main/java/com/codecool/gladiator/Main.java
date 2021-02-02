package com.codecool.gladiator;

import com.codecool.gladiator.controller.Colosseum;
import com.codecool.gladiator.model.gladiators.GladiatorFactory;
import com.codecool.gladiator.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        ConsoleView cv = new ConsoleView();
        cv.display("Hello world!");
        int n = cv.getNumberBetween(0, 10);
        cv.display("Given number: " + n);
//        Colosseum colosseum = new Colosseum(new ConsoleView(), new GladiatorFactory("Names.txt"));
//        colosseum.welcomeAndAskForStages();
//        colosseum.runSimulation();
    }

}
