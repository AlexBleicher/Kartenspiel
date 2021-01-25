package Kartenspiel;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    public void testRowfinder(){

        Player testPlayer=new Player();
        for (int i = 0; i <= 13; i++) {
            testPlayer.draw(new Card(CardColor.HEART, i));
        }

        List<List<Card>> allRows= testPlayer.getAllRows();

        assertThat(allRows.size()).isEqualTo(11);
    }

}