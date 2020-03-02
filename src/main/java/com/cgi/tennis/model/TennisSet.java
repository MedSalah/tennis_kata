package com.cgi.tennis.model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import static com.cgi.tennis.model.types.StateType.ENDED;
import static java.lang.String.format;

public class TennisSet extends ScoreEntity {

    private List<AbstractGame> games = new ArrayList<>();
    private AbstractGame activeGame;

    public TennisSet(Player player1, Player player2) {
        super(player1, player2);
        beginNewGame();
    }

    @Override
    public void increasePlayerScore(Player player) {
        if (isEnded()) {
            throw new IllegalStateException("Game already Ended");
        }

        this.activeGame.increasePlayerScore(player);
        AbstractMap.SimpleEntry<Player, Integer> playerScoreEntry = getPlayerScoreEntry(player);
        AbstractMap.SimpleEntry<Player, Integer> opponentScoreEntry = getOpponentScoreEntry(player);

        if (activeGame.isEnded()) {
            increase(playerScoreEntry);

            if (activeGame instanceof TieBreakGame) {
                endSet();
                return;
            }

            if (playerScoreEntry.getValue() == 6 && playerScoreEntry.getValue().equals(opponentScoreEntry.getValue())) {
                playTieBreakGame();
                return;
            }

            if (playerScoreEntry.getValue() == 6 && (playerScoreEntry.getValue() - opponentScoreEntry.getValue() >= 2)) {
                endSet();
                return;
            }
            if (playerScoreEntry.getValue() == 7 && (playerScoreEntry.getValue() - opponentScoreEntry.getValue() > 1)) {
                endSet();
                return;
            }

            beginNewGame();
        }
    }

    @Override
    public void printScore() {
        System.out.print(
                format("(%s - %s)", getPlayer1ScoreEntry().getValue(), getPlayer2ScoreEntry().getValue())
        );
    }

    public AbstractGame getActiveGame() {
        return activeGame;
    }

    private void playTieBreakGame(){
        TieBreakGame tieBreakGame = new TieBreakGame(getPlayer1ScoreEntry().getKey(), getPlayer2ScoreEntry().getKey());
        this.activeGame = tieBreakGame;
        this.games.add(tieBreakGame);
    }

    private void beginNewGame() {
        Game game = new Game(getPlayer1ScoreEntry().getKey(), getPlayer2ScoreEntry().getKey());
        this.activeGame = game;
        this.games.add(game);
    }

    private void endSet() {
        this.activeGame = null;
        setState(ENDED);
    }
}
