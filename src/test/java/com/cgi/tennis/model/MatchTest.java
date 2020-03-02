package com.cgi.tennis.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchTest {

    private Match match;
    private Player federer = new Player("Federer");
    private Player nadal = new Player("Nadal");

    @Before
    public void setup() {
        match = new Match(federer, nadal);
    }

    @Test
    public void match_should_start_with_score_of_0_0(){
        assertThat(match.getPlayerScoreEntry(federer).getValue()).isEqualTo(0);
        assertThat(match.getPlayerScoreEntry(nadal).getValue()).isEqualTo(0);
        assertThat(match.isInProgress()).isTrue();
    }

    @Test
    public void match_first_set_won_by_Federer(){
        winSet(federer);

        assertThat(match.getPlayerScoreEntry(federer).getValue()).isEqualTo(1);
        assertThat(match.getPlayerScoreEntry(nadal).getValue()).isEqualTo(0);
        assertThat(match.isInProgress()).isTrue();
    }

    @Test
    public void match_second_set_won_by_Nadal(){
        winSet(federer);
        winSet(nadal);

        assertThat(match.getPlayerScoreEntry(federer).getValue()).isEqualTo(1);
        assertThat(match.getPlayerScoreEntry(nadal).getValue()).isEqualTo(1);
        assertThat(match.isInProgress()).isTrue();
    }

    @Test
    public void match_won_by_Nadal(){
        winSet(federer);
        winSet(nadal);
        winSet(nadal);
        winSet(nadal);

        assertThat(match.getPlayerScoreEntry(federer).getValue()).isEqualTo(1);
        assertThat(match.getPlayerScoreEntry(nadal).getValue()).isEqualTo(3);
        assertThat(match.isEnded()).isTrue();
    }

    @Test(expected = IllegalStateException.class)
    public void match_match_over(){
        winSet(federer);
        winSet(nadal);
        winSet(nadal);
        winSet(nadal);

        //Extra Set
        winSet(nadal);
    }

    private void winSet(Player player) {
        for (int i = 0; i < 24; i++) {
            match.increasePlayerScore(player);
        }
    }

}
