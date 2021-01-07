package Kartenspiel;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class SetTest {

    @Test
    void testGenerate(){
        Set startSet=new Set(new Game());

        startSet.generateCards();

        assertThat(startSet.getCards().size()).isEqualTo(4*14);
    }
    @Test
    void testMix(){
        Set startSet=new Set(new Game());

        Set compareSet=new Set(new Game());

        startSet.generateCards();
        compareSet.generateCards();

        startSet.mix();

        assertThat(startSet).isNotEqualTo(compareSet);

    }

}