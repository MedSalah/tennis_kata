package com.cgi.tennis;

import com.cgi.tennis.model.Match;
import com.cgi.tennis.model.Player;

public class Example4 {

    public static void main(String[] args) {
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");

        Match match = new Match(federer, nadal);

        /* First set */
        winGames(match, federer, 5);
        winGames(match, nadal, 1);
        winGames(match, federer, 1);

        /* Second set */
        winGames(match, federer, 5);
        winGames(match, nadal, 5);
        winGames(match, federer, 2);

        /* Third set */
        winGames(match, federer, 6);

        match.printScore();
    }

    private static void winGames(Match match, Player player, int numberOfGames) {
        for (int i = 0; i < 4 * numberOfGames; i++) {
            match.increasePlayerScore(player);
        }
    }
}
