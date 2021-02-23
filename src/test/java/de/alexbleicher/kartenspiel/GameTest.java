package de.alexbleicher.kartenspiel;

import de.alexbleicher.kartenspiel.logik.Game;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    @Test
    void testStart() {
        Game game = new Game();

        game.startGame();

        //assertThat(game.playerList.get(3).getHand().size()).isEqualTo(13);
        assertThat(game.getDiscardPile().size()).isEqualTo(1);
    }

    @Test
    void testGenerateCards(){
        Game game=new Game();
        game.generateCards();

        assertThat(game.getSet().size()).isEqualTo(4*14);
    }

}