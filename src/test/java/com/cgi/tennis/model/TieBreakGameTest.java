package com.cgi.tennis.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TieBreakGameTest {

    private TieBreakGame tieBreakGame;
    private Player federer = new Player("Federer");
    private Player nadal = new Player("Nadal");

    @Before
    public void setup() {
        tieBreakGame = new TieBreakGame(federer, nadal);
    }

    @Test
    public void tieBreakGame_should_start_with_score_of_0_0() {
        assertThat(tieBreakGame.getPlayerScoreEntry(federer).getValue()).isEqualTo(0);
        assertThat(tieBreakGame.getPlayerScoreEntry(nadal).getValue()).isEqualTo(0);
        assertThat(tieBreakGame.isInProgress()).isTrue();
    }

    @Test
    public void tieBreakGame_federer_1_0(){
        tieBreakGame.increasePlayerScore(federer);

        assertThat(tieBreakGame.getPlayerScoreEntry(federer).getValue()).isEqualTo(1);
        assertThat(tieBreakGame.getPlayerScoreEntry(nadal).getValue()).isEqualTo(0);
        assertThat(tieBreakGame.isInProgress()).isTrue();
    }

    @Test
    public void tieBreakGame_federer_5_0(){
        score(federer, 5);
        assertThat(tieBreakGame.getPlayerScoreEntry(federer).getValue()).isEqualTo(5);
        assertThat(tieBreakGame.getPlayerScoreEntry(nadal).getValue()).isEqualTo(0);
        assertThat(tieBreakGame.isInProgress()).isTrue();
    }

    @Test
    public void tieBreakGame_draw_5_5(){
        score(federer, 5);
        score(nadal, 5);

        assertThat(tieBreakGame.getPlayerScoreEntry(federer).getValue()).isEqualTo(5);
        assertThat(tieBreakGame.getPlayerScoreEntry(nadal).getValue()).isEqualTo(5);
        assertThat(tieBreakGame.isInProgress()).isTrue();
    }

    @Test
    public void tieBreakGame_nadal_7_5(){
        score(federer, 5);
        score(nadal, 7);

        assertThat(tieBreakGame.getPlayerScoreEntry(federer).getValue()).isEqualTo(5);
        assertThat(tieBreakGame.getPlayerScoreEntry(nadal).getValue()).isEqualTo(7);
        assertThat(tieBreakGame.isEnded()).isTrue();
    }

    private void score(Player player, int score) {
        for (int i = 0; i< score; i++) {
            tieBreakGame.increasePlayerScore(player);
        }
    }

}
