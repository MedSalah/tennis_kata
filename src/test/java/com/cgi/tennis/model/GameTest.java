package com.cgi.tennis.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private Game game;
    private Player federer = new Player("Federer");
    private Player nadal = new Player("Nadal");

    @Before
    public void setup() {
        game = new Game(federer, nadal);
    }

    @Test
    public void game_should_start_with_score_of_0_0(){
        assertThat(game.getPlayerScoreEntry(federer).getValue()).isEqualTo(0);
        assertThat(game.getPlayerScoreEntry(nadal).getValue()).isEqualTo(0);
        assertThat(game.isInProgress()).isTrue();
    }

    @Test
    public void game_federer_15_0(){
        game.increasePlayerScore(federer);

        assertThat(game.getPlayerScoreEntry(federer).getValue()).isEqualTo(1);
        assertThat(game.getPlayerScoreEntry(nadal).getValue()).isEqualTo(0);
        assertThat(game.isInProgress()).isTrue();
    }

    @Test
    public void game_nadal_15_40(){
        game.increasePlayerScore(federer);

        game.increasePlayerScore(nadal);
        game.increasePlayerScore(nadal);
        game.increasePlayerScore(nadal);

        assertThat(game.getPlayerScoreEntry(federer).getValue()).isEqualTo(1);
        assertThat(game.getPlayerScoreEntry(nadal).getValue()).isEqualTo(3);
        assertThat(game.isInProgress()).isTrue();
    }

    @Test
    public void game_deuce(){
        game.increasePlayerScore(federer);

        game.increasePlayerScore(nadal);
        game.increasePlayerScore(nadal);
        game.increasePlayerScore(nadal);

        game.increasePlayerScore(federer);
        game.increasePlayerScore(federer);

        assertThat(game.getPlayerScoreEntry(federer).getValue()).isEqualTo(3);
        assertThat(game.getPlayerScoreEntry(nadal).getValue()).isEqualTo(3);
        assertThat(game.isDeuce()).isTrue();
    }

    @Test
    public void game_advantage_federer(){
        game.increasePlayerScore(federer);

        game.increasePlayerScore(nadal);
        game.increasePlayerScore(nadal);
        game.increasePlayerScore(nadal);

        game.increasePlayerScore(federer);
        game.increasePlayerScore(federer);

        // Advantage
        game.increasePlayerScore(nadal);

        assertThat(game.getPlayerScoreEntry(federer).getValue()).isEqualTo(3);
        assertThat(game.getPlayerScoreEntry(nadal).getValue()).isEqualTo(4);
        assertThat(game.isAdvantage()).isTrue();
    }

    @Test
    public void game_deuce_again(){
        game.increasePlayerScore(federer);

        game.increasePlayerScore(nadal);
        game.increasePlayerScore(nadal);
        game.increasePlayerScore(nadal);

        game.increasePlayerScore(federer);
        game.increasePlayerScore(federer);

        // Advantage
        game.increasePlayerScore(nadal);

        // Deuce
        game.increasePlayerScore(federer);

        assertThat(game.getPlayerScoreEntry(federer).getValue()).isEqualTo(3);
        assertThat(game.getPlayerScoreEntry(nadal).getValue()).isEqualTo(3);
        assertThat(game.isDeuce()).isTrue();
    }

    @Test
    public void game_nadal(){
        game.increasePlayerScore(federer);

        game.increasePlayerScore(nadal);
        game.increasePlayerScore(nadal);
        game.increasePlayerScore(nadal);

        game.increasePlayerScore(federer);
        game.increasePlayerScore(federer);

        // Advantage
        game.increasePlayerScore(nadal);

        // Game Win
        game.increasePlayerScore(nadal);

        assertThat(game.getPlayerScoreEntry(federer).getValue()).isEqualTo(3);
        assertThat(game.getPlayerScoreEntry(nadal).getValue()).isEqualTo(5);
        assertThat(game.isEnded()).isTrue();
    }

}
