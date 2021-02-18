package com.codecool.gladiator.util;

import com.codecool.gladiator.model.Contestants;

import java.util.List;

/**
 * Custom implementation of the binary tree data structure.
 */
public class Tournament {

    private Contestants contestants;  // must be null if there are further branches
    private Tournament leftBranch;    // must be null if there are contestants
    private Tournament rightBranch;   // must be null if there are contestants
    private int size;                 // the size of the tree
    private boolean left = true;      // value used for navigating between left and right branches

    /**
     * Constructor with initial value.
     *
     * @param contestants the initial value to be added to the tree
     */
    public Tournament(Contestants contestants) {
        add(contestants);
    }

    /**
     * Constructor with initial list of values.
     *
     * @param values the list of values to be added to the tree
     */
    public Tournament(List<Contestants> values) {
        addAll(values);
    }

    /**
     * Getter for the value (null if there are further branches).
     *
     * @return the value
     */
    public Contestants getContestants() {
        return contestants;
    }

    /**
     * Getter for the left branch (null if there are contestants).
     *
     * @return the left branch
     */
    public Tournament getLeftBranch() {
        return leftBranch;
    }

    /**
     * Getter for the right branch (null if there are contestants).
     *
     * @return the right branch
     */
    public Tournament getRightBranch() {
        return rightBranch;
    }

    /**
     * Setter for current contestants.
     *
     * @param contestants contestants of the Tournament
     */
    public void setContestants(Contestants contestants) {
        if (leftBranch != null || rightBranch != null)
            throw new IllegalStateException("There should be no branches to set up the contestants.");

        this.contestants = contestants;
    }

    /**
     * Returns the number of values put in the tree.
     *
     * @return the size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * Adds a new value to the tree.
     *
     * @param contestants the value to be added to the tree.
     */
    public void add(Contestants contestants) {
        if (hasNoBranches())
            if (isEmpty())
                setNewContestants(contestants);
            else  // has contestants
                setNewTournament(contestants);
        else  // has branch/branches
            setNewBranchAndNewContestants(contestants);

        size++;
    }

    private boolean hasNoBranches() {
        return leftBranch == null && rightBranch == null;
    }

    private boolean isEmpty() {
        return this.contestants == null;
    }

    private void setNewContestants(Contestants contestants) {
        this.contestants = contestants;
    }

    private void setNewTournament(Contestants contestants) {
        leftBranch = new Tournament(this.contestants);  // push the parent contestants higher
        rightBranch = new Tournament(contestants);
        this.contestants = null;
    }

    private void setNewBranchAndNewContestants(Contestants contestants) {
        Tournament newBranch = left ? leftBranch : rightBranch;
        newBranch.add(contestants);
        left = !left;
    }

    /**
     * Adds multiple values to the tree.
     *
     * @param listOfContestants the list of values to be added to the tree
     */
    public void addAll(List<Contestants> listOfContestants) {
        for (Contestants contestants : listOfContestants)
            add(contestants);
    }
}
