package com.cgi.tennis.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TennisSetTest {

    private TennisSet tennisSet;
    private Player federer = new Player("Federer");
    private Player nadal = new Player("Nadal");

    @Before
    public void setup() {
        tennisSet = new TennisSet(federer, nadal);
    }

    @Test
    public void tennisSet_should_start_with_score_of_0_0() {
        assertThat(tennisSet.getPlayerScoreEntry(federer).getValue()).isEqualTo(0);
        assertThat(tennisSet.getPlayerScoreEntry(nadal).getValue()).isEqualTo(0);
        assertThat(tennisSet.isInProgress()).isTrue();
    }

    @Test
    public void tennisSet_nadal_6_0() {
        winGame(nadal, 6);

        assertThat(tennisSet.getPlayerScoreEntry(federer).getValue()).isEqualTo(0);
        assertThat(tennisSet.getPlayerScoreEntry(nadal).getValue()).isEqualTo(6);
        assertThat(tennisSet.isEnded()).isTrue();
    }

    @Test
    public void tennisSet_nadal_7_5() {
        winGame(nadal, 4);
        winGame(federer, 5);
        winGame(nadal, 3);

        assertThat(tennisSet.getPlayerScoreEntry(federer).getValue()).isEqualTo(5);
        assertThat(tennisSet.getPlayerScoreEntry(nadal).getValue()).isEqualTo(7);
        assertThat(tennisSet.isEnded()).isTrue();
    }

    @Test
    public void tennisSet_nadal_7_6() {
        winGame(nadal, 4);
        winGame(federer, 5);
        winGame(nadal, 1);
        winGame(federer, 1);
        winGame(nadal, 1);
        winTieBreak(nadal);

        assertThat(tennisSet.getPlayerScoreEntry(federer).getValue()).isEqualTo(6);
        assertThat(tennisSet.getPlayerScoreEntry(nadal).getValue()).isEqualTo(7);
        assertThat(tennisSet.isEnded()).isTrue();
    }

    private void winGame(Player player, int numberOfGames) {
        for (int i = 0; i < 4 * numberOfGames; i++) {
            tennisSet.increasePlayerScore(player);
        }
    }

    private void winTieBreak(Player player) {
        for (int i = 0; i < 7; i++) {
            tennisSet.increasePlayerScore(player);
        }
    }
}
