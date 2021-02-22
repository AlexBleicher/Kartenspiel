package Kartenspiel;

import Kartenspiel.Logik.Game;

public class testMain {
    public static void main(String[] args) {
        Game testGame=new Game();
        testGame.generatePlayers();
        testGame.playRound();
    }
}
