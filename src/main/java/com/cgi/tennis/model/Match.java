package com.cgi.tennis.model;


import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import static com.cgi.tennis.model.types.StateType.ENDED;

public class Match extends ScoreEntity {

    private List<TennisSet> tennisSets = new ArrayList<>();
    private TennisSet activeTennisSet;

    public Match(Player player1, Player player2) {
        super(player1, player2);
        beginNewSet();
    }

    @Override
    public void increasePlayerScore(Player player) {
        if (isEnded()) {
            throw new IllegalStateException("Game already Ended");
        }

        activeTennisSet.increasePlayerScore(player);
        AbstractMap.SimpleEntry<Player, Integer> playerScoreEntry = getPlayerScoreEntry(player);

        if (activeTennisSet.isEnded()) {
            increase(playerScoreEntry);

            if (playerScoreEntry.getValue() == 3) {
                endSet();
                return;
            }
            beginNewSet();
        }
    }

    @Override
    public void printScore() {
        System.out.println("Player 1: " + getPlayer1ScoreEntry().getKey().getName());
        System.out.println("Player 2: " + getPlayer2ScoreEntry().getKey().getName());
        System.out.print("Score: ");
        tennisSets.forEach(TennisSet::printScore);
        System.out.print("\n\r");

        if (isInProgress()) {
            activeTennisSet.getActiveGame().printScore();
            System.out.println("Match Status: In Progress");
            return;
        }
        System.out.println("Match Status: " + getWinner() + " wins");
    }

    private void beginNewSet() {
        TennisSet tennisSet = new TennisSet(getPlayer1ScoreEntry().getKey(), getPlayer2ScoreEntry().getKey());
        this.activeTennisSet = tennisSet;
        this.tennisSets.add(tennisSet);
    }

    private void endSet() {
        activeTennisSet = null;
        setState(ENDED);
    }

    private String getWinner() {
        return getPlayer1ScoreEntry().getValue() > getPlayer2ScoreEntry().getValue() ? "Player1" : "Player2";
    }

}
