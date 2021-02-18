package com.codecool.gladiator.controller;

import com.codecool.gladiator.model.Combat;
import com.codecool.gladiator.model.Contestants;
import com.codecool.gladiator.model.GladiatorFactory;
import com.codecool.gladiator.model.gladiators.*;
import com.codecool.gladiator.util.Randomizer;
import com.codecool.gladiator.util.Tournament;
import com.codecool.gladiator.util.Util;
import com.codecool.gladiator.view.Viewable;

import java.util.ArrayList;
import java.util.List;

public class Colosseum {

    public static final int MIN_TOURNAMENT_STAGES = 1;
    public static final int MAX_TOURNAMENT_STAGES = 10;

    private final Viewable view;
    private final GladiatorFactory gladiatorFactory;
    private int stages = 2;
    private int stageNumber = 1;
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
        announcePairs(contestants);
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
        return pairs;
    }

    private Gladiator getChampion(Tournament stage) {
        if (stage == null)
            throw new IllegalArgumentException("It is not possible to choose a winner when the tournament is empty.");

        var nextStageGladiators = runStage(stage);
        while (shouldFightAgain(nextStageGladiators)) {
            stageNumber++;
            var pairs = splitGladiatorsIntoPairs(nextStageGladiators);
            announcePairs(pairs);

            nextStageGladiators = runStage(new Tournament(pairs));
            increaseLevelOfWinners(nextStageGladiators);
        }

        return nextStageGladiators.get(0);  // there should be only one on the list
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
        announceCombat(combat);

        combat.simulate();
        combatNumber++;

        displayCombatLog(combat);
        announceWinnerAndLoser(combat);
        return combat.getGladiator1();
    }

    private boolean shouldFightAgain(List<Gladiator> gladiators) {
        return gladiators.size() != 1;
    }

    private void increaseLevelOfWinners(List<Gladiator> nextStageGladiators) {
        for (Gladiator gladiator : nextStageGladiators)
            gladiator.levelUp();
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
        view.display(String.format("\nPrepare to stage %s...\nThe warriors were assigned to the following pairs:", stageNumber));
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

    private void announceCombat(Combat combat) {
        view.display(String.format("\nDuel #%s: %s versus %s:",
                combatNumber,
                combat.getGladiator1().getName(),
                combat.getGladiator2().getName()));

        for (Gladiator gladiator : combat.getGladiators()) {
            view.display(String.format(" - %s (%s HP, %s SP, %s DEX, %s LVL)",
                    gladiator,
                    Util.formatNumber(gladiator.getHp()),
                    Util.formatNumber(gladiator.getSp()),
                    Util.formatNumber(gladiator.getDex()),
                    gladiator.getLevel()));
        }

        view.display("\nBegin!");
    }

    private void announceWinnerAndLoser(Combat combat) {
        view.display(String.format("%s has died, %s wins!", combat.getGladiator2(), combat.getGladiator1()));
    }

    private void displayCombatLog(Combat combat) {
        view.display(String.format(" - %s", combat.getCombatLog("\n - ")));
    }
}
