package com.cgi.tennis.model;

import java.util.AbstractMap;

import static com.cgi.tennis.model.types.StateType.ENDED;
import static java.lang.String.format;

public class TieBreakGame extends AbstractGame {

    public TieBreakGame(Player player1, Player player2) {
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
            if (playerScoreEntry.getValue() >= 7 && (playerScoreEntry.getValue() - opponentScoreEntry.getValue()) >= 2) {
                setState(ENDED);
            }
        }
    }

    @Override
    public void printScore() {
        System.out.println(
                format("Current game Score: (%s - %s)", getPlayer1ScoreEntry().getValue(), getPlayer2ScoreEntry().getValue())
        );
    }
}
