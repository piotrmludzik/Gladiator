package com.codecool.gladiator.controller;

import com.codecool.gladiator.model.Combat;
import com.codecool.gladiator.model.Contestants;
import com.codecool.gladiator.model.GladiatorFactory;
import com.codecool.gladiator.model.gladiators.*;
import com.codecool.gladiator.util.Randomizer;
import com.codecool.gladiator.util.Tournament;
import com.codecool.gladiator.view.Viewable;

import java.util.ArrayList;
import java.util.List;

public class Colosseum {

    public static final int MIN_TOURNAMENT_STAGES = 1;
    public static final int MAX_TOURNAMENT_STAGES = 10;

    private final Viewable view;
    private final GladiatorFactory gladiatorFactory;
    private int stages = 2;
    private int combatNumber = 1;

    public Colosseum(Viewable view, GladiatorFactory gladiatorFactory) {
        this.view = view;
        this.gladiatorFactory = gladiatorFactory;
    }

    /**
     * Runs the Tournament simulation
     */
    public void runSimulation() {
        var numberOfGladiators = (int) Math.pow(2, stages);
        var gladiators = generateGladiators(numberOfGladiators);
        var contestants = splitGladiatorsIntoPairs(gladiators);
        var tournamentTree = new Tournament(contestants);
        var champion = getChampion(tournamentTree);
        announceChampion(champion);

        // The following line chains the above lines:
        // announceChampion(getChampion(new BinaryTree<>(generateGladiators((int) Math.pow(2, stages)))));
    }

    private List<Gladiator> generateGladiators(int numberOfGladiators) {
        List<Gladiator> gladiators = new ArrayList<>();
        for (int n=0; n < numberOfGladiators; n++)
            gladiators.add(gladiatorFactory.generateRandomGladiator());

        introduceGladiators(gladiators);
        return gladiators;
    }

    private List<Contestants> splitGladiatorsIntoPairs(List<Gladiator> gladiators) {
        List<Contestants> pairs = new ArrayList<>();

        int pairsNumber = gladiators.size() / 2;
        for (int n=1; n <= pairsNumber; n++) {
            Gladiator firstGladiator = Randomizer.chooseOne(gladiators);
            gladiators.remove(firstGladiator);
            Gladiator secondGladiator = Randomizer.chooseOne(gladiators);
            gladiators.remove(secondGladiator);
            pairs.add(new Contestants(firstGladiator, secondGladiator));
        }

        announcePairs(pairs);
        return pairs;
    }

    private Gladiator getChampion(Tournament stage) {
        if (stage == null)
            throw new IllegalArgumentException("It is not possible to choose a winner when the tournament is empty.");

        var nextStageGladiators = runStage(stage);
        while (shouldFightAgain(nextStageGladiators)) {
            stage = new Tournament(splitGladiatorsIntoPairs(nextStageGladiators));
            nextStageGladiators = runStage(stage);
        }

        return nextStageGladiators.get(0);
    }

    private boolean shouldFightAgain(List<Gladiator> gladiators) {
        return gladiators.size() != 1;
    }

    private List<Gladiator> runStage(Tournament stage) {
        var victoriousGladiators = new ArrayList<Gladiator>();
        for (Contestants contestants : getStageContestants(stage)) {
            Gladiator winner = simulateCombat(new Combat(contestants));
            victoriousGladiators.add(winner);
        }

        return victoriousGladiators;
    }

    private List<Contestants> getStageContestants(Tournament stage) {
        if (hasNoBranches(stage))
            return List.of(stage.getContestants());

        var contestants = new ArrayList<Contestants>();
        var leftBranch = stage.getLeftBranch();
        var rightBranch = stage.getRightBranch();

        if (hasBranch(leftBranch))
            contestants.addAll(getStageContestants(stage.getLeftBranch()));
        if (hasBranch(rightBranch))
            contestants.addAll(getStageContestants(stage.getRightBranch()));

        return contestants;
    }

    private boolean hasNoBranches(Tournament tournament) {
        return tournament.getLeftBranch() == null && tournament.getRightBranch() == null;
    }

    private boolean hasBranch(Tournament branch) {
        return branch != null;
    }

    private Gladiator simulateCombat(Combat combat) {
        Gladiator gladiator1 = combat.getAttacker();
        Gladiator gladiator2 = combat.getDefender();
        announceCombat(gladiator1, gladiator2);

        combat.simulate();
        combatNumber++;

        displayCombatLog(combat);
        announceWinnerAndLoser(gladiator1, gladiator2);
        return gladiator1;
    }

    public void welcome() {
        view.display("Ave Caesar, and welcome to the Colosseum!");
    }

    public void welcomeAndAskForStages() {
        welcome();
        view.display("How many stages of the Tournament do you wish to watch?");
        stages = view.getNumberBetween(MIN_TOURNAMENT_STAGES, MAX_TOURNAMENT_STAGES);
    }

    private void introduceGladiators(List<Gladiator> gladiators) {
        view.display(String.format("\nWe have selected Rome's %d finest warriors for today's Tournament!", gladiators.size()));
        for (Gladiator gladiator: gladiators) {
            view.display(String.format(" - %s", gladiator));
        }
        view.display("\n\"Ave Imperator, morituri te salutant!\"");
    }

    private void announcePairs(List<Contestants> pairs) {
        System.out.println("\nThe warriors were assigned to the following pairs:");
        for (int n=0; n < pairs.size(); n++)
            System.out.println(((n + 1) + ": " + pairs.get(n)));
    }

    private void announceChampion(Gladiator champion) {
        if (champion != null) {
            view.display(String.format("\nThe Champion of the Tournament is %s!", champion.getFullName()));
        } else {
            view.display("\nHave mercy, Caesar, the Tournament will start soon!");
        }
    }

    private void announceCombat(Gladiator gladiator1, Gladiator gladiator2) {
        view.display(String.format("\nDuel #%s: %s versus %s:", combatNumber, gladiator1.getName(), gladiator2.getName()));
        view.display(String.format(" - %s", gladiator1));
        view.display(String.format(" - %s", gladiator2));
    }

    private void announceWinnerAndLoser(Gladiator winner, Gladiator loser) {
        view.display(String.format("%s has died, %s wins!", loser.getFullName(), winner.getFullName()));
    }

    private void displayCombatLog(Combat combat) {
        view.display(String.format(" - %s", combat.getCombatLog(", ")));
    }
}
