package com.cgi.tennis.model;


import java.util.AbstractMap;

import static com.cgi.tennis.model.types.StateType.*;

public class Game extends AbstractGame {

    public Game(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public void increasePlayerScore(Player player) {
        if (isEnded()) {
            throw new IllegalStateException("Game already Ended");
        }

        AbstractMap.SimpleEntry<Player, Integer> playerScoreEntry = getPlayerScoreEntry(player);
        AbstractMap.SimpleEntry<Player, Integer> opponentScoreEntry = getOpponentScoreEntry(player);

        if (isInProgress()) {
            increase(playerScoreEntry);
            if (playerScoreEntry.getValue().equals(opponentScoreEntry.getValue()) && playerScoreEntry.getValue() == 3) {
                setState(DEUCE);
                return;
            }

            if (playerScoreEntry.getValue() > 3) {
                setState(ENDED);
                return;
            }
        }

        if(isDeuce()) {
            increase(playerScoreEntry);
            setState(ADVANTAGE);
            return;
        }

        if(isAdvantage()) {
            if (playerScoreEntry.getValue() > opponentScoreEntry.getValue()) {
                increase(playerScoreEntry);
                setState(ENDED);
                return;
            }

            if (playerScoreEntry.getValue() < opponentScoreEntry.getValue()) {
                decrease(opponentScoreEntry);
                setState(DEUCE);
            }
        }
    }

    @Override
    public void printScore() {
        switch (getState()) {
            case IN_PROGRESS:
                String player1Score = translateTennisScore(getPlayer1ScoreEntry().getValue());
                String player2Score = translateTennisScore(getPlayer2ScoreEntry().getValue());

                System.out.println(String.format("Current game Score: (%s - %s)", player1Score, player2Score));
                break;
            case DEUCE:
                System.out.println("Current game Score: Deuce");
                break;
            case ADVANTAGE:
                System.out.println("Current game Score: Advantage");
                break;
            default:
                break;
        }
    }

    private String translateTennisScore(Integer score) {
        switch (score) {
            case 0:
                return "0";
            case 1:
                return "15";
            case 2:
                return "30";
            default:
                return "40";
        }
    }
}
