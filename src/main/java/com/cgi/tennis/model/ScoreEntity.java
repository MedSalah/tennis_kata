package com.cgi.tennis.model;

import com.cgi.tennis.model.types.StateType;

import java.util.AbstractMap;

import static com.cgi.tennis.model.types.StateType.*;
import static com.cgi.tennis.model.types.StateType.ENDED;
import static org.apache.commons.lang3.Validate.notNull;

public abstract class ScoreEntity {

    private AbstractMap.SimpleEntry<Player, Integer> player1ScoreEntry;
    private AbstractMap.SimpleEntry<Player, Integer> player2ScoreEntry;
    private StateType state = IN_PROGRESS;

    public ScoreEntity(Player player1, Player player2) {
        notNull(player1, "player1 should not be null");
        notNull(player2, "player2 should not be null");

        player1ScoreEntry = new AbstractMap.SimpleEntry<>(player1, 0);
        player2ScoreEntry = new AbstractMap.SimpleEntry<>(player2, 0);
    }

    public abstract void increasePlayerScore(Player player);
    public abstract void printScore();

    public boolean isInProgress() {
        return this.state == IN_PROGRESS;
    }

    public boolean isDeuce() {
        return this.state == DEUCE;
    }

    public boolean isAdvantage() {
        return this.state == ADVANTAGE;
    }

    public boolean isEnded() {
        return this.state == ENDED;
    }

    public StateType getState() {
        return state;
    }

    public void setState(StateType state) {
        this.state = state;
    }

    protected AbstractMap.SimpleEntry<Player, Integer> getPlayerScoreEntry(Player player) {
        if (player1ScoreEntry.getKey().equals(player)) {
            return player1ScoreEntry;
        }
        return player2ScoreEntry;
    }

    protected AbstractMap.SimpleEntry<Player, Integer> getOpponentScoreEntry(Player player) {
        if (!player1ScoreEntry.getKey().equals(player)) {
            return player1ScoreEntry;
        }
        return player2ScoreEntry;
    }

    public AbstractMap.SimpleEntry<Player, Integer> getPlayer1ScoreEntry() {
        return player1ScoreEntry;
    }

    public AbstractMap.SimpleEntry<Player, Integer> getPlayer2ScoreEntry() {
        return player2ScoreEntry;
    }

    protected void increase(AbstractMap.SimpleEntry<Player, Integer> playerScoreEntry) {
        playerScoreEntry.setValue(playerScoreEntry.getValue() +1);
    }

    protected void decrease(AbstractMap.SimpleEntry<Player, Integer> playerScoreEntry) {
        playerScoreEntry.setValue(playerScoreEntry.getValue() -1);
    }
}
