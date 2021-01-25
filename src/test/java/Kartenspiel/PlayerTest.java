package Kartenspiel;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    public void testRowfinder(){

        Game game=new Game();
        game.generatePlayers();
        game.startGame();

        game.playerList.get(0).getAllRows();


        assertThat(true);
    }

}