package de.alexbleicher.kartenspiel;

import de.alexbleicher.kartenspiel.logik.Game;

public class testMain {
    public static void main(String[] args) {
        Game testGame = new Game();
        testGame.generatePlayers();
        //testGame.playRound();
    }
}
