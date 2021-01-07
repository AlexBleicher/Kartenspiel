package Kartenspiel;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testStart() {
        Game game = new Game();

        game.startRound();

        assertThat(game.playerList.get(3).getHand().size()).isEqualTo(13);
    }

}