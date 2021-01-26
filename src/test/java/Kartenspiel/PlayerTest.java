package Kartenspiel;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
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

    @Test
    public void testPairFinder(){
        Player testPlayer=new Player();
        Card card1=new Card(CardColor.HEART,1);
        Card card2=new Card(CardColor.HEART, 1);
        Card card3=new Card(CardColor.SPADES, 1);
        Card card4=new Card(CardColor.CHECK,1);

        Card card5=new Card(CardColor.CHECK, 3);
        Card card6=new Card(CardColor.CROSS, 3);

        Card card7=new Card(CardColor.HEART, 5);
        Card card8=new Card(CardColor.SPADES, 5);
        Card card9=new Card(CardColor.CHECK, 5);
        Card card10=new Card(CardColor.CROSS, 5);

        testPlayer.draw(card1);
        testPlayer.draw(card2);
        testPlayer.draw(card3);
        testPlayer.draw(card4);
        testPlayer.draw(card5);
        testPlayer.draw(card6);
        testPlayer.draw(card7);
        testPlayer.draw(card8);
        testPlayer.draw(card9);
        testPlayer.draw(card10);
        List<Card> allDuplicates= testPlayer.getAllDuplicates();
        List<List<Card>> allPairs= testPlayer.getAllPairs(allDuplicates);

        assertThat(allPairs.size()).isEqualTo(2);
    }

    @Test
    void testOrganize(){
        Player testPlayer=new Player();
        Card card1=new Card(CardColor.HEART,1);
        Card card2=new Card(CardColor.HEART, 1);
        Card card3=new Card(CardColor.SPADES, 1);
        Card card4=new Card(CardColor.CHECK,1);

        Card card5=new Card(CardColor.CHECK, 3);
        Card card6=new Card(CardColor.CROSS, 3);

        Card card7=new Card(CardColor.HEART, 5);
        Card card8=new Card(CardColor.SPADES, 5);
        Card card9=new Card(CardColor.CHECK, 5);
        Card card10=new Card(CardColor.CROSS, 5);

        Card card11=new Card(CardColor.HEART, 4);
        Card card12=new Card(CardColor.HEART, 6);
        Card card13=new Card(CardColor.HEART, 7);
        Card card14=new Card(CardColor.HEART, 5);

        testPlayer.draw(card1);
        testPlayer.draw(card2);
        testPlayer.draw(card3);
        testPlayer.draw(card4);
        testPlayer.draw(card5);
        testPlayer.draw(card6);
        testPlayer.draw(card7);
        testPlayer.draw(card8);
        testPlayer.draw(card9);
        testPlayer.draw(card10);
        testPlayer.draw(card11);
        testPlayer.draw(card12);
        testPlayer.draw(card13);
        testPlayer.draw(card14);
        assertThat(testPlayer.organizeHand()).isEqualTo(54);
    }
    @Test
    public void testDuplicateFinder(){
        Player testPlayer=new Player();
        Card card1=new Card(CardColor.HEART,1);
        Card card2=new Card(CardColor.HEART, 1);
        Card card3=new Card(CardColor.SPADES, 1);
        Card card4=new Card(CardColor.CHECK,1);
        testPlayer.draw(card1);
        testPlayer.draw(card2);
        testPlayer.draw(card3);
        testPlayer.draw(card4);
        List<Card> allDuplicates=testPlayer.getAllDuplicates();
        assertThat(allDuplicates.size()).isEqualTo(1);
    }
    @Test
    public void testIsPossible(){
        Player testPlayer=new Player();
        Card card1=new Card(CardColor.HEART,1);
        Card card2=new Card(CardColor.HEART, 1);
        Card card3=new Card(CardColor.SPADES, 1);
        Card card4=new Card(CardColor.CHECK,1);

        Card card5=new Card(CardColor.CHECK, 3);
        Card card6=new Card(CardColor.CROSS, 3);

        Card card7=new Card(CardColor.HEART, 6);
        Card card8=new Card(CardColor.SPADES, 6);
        Card card9=new Card(CardColor.CHECK, 6);
        Card card10=new Card(CardColor.CROSS, 6);

        Card card11=new Card(CardColor.HEART, 5);
        Card card12=new Card(CardColor.HEART, 6);
        Card card13=new Card(CardColor.HEART, 7);

        testPlayer.draw(card1);
        testPlayer.draw(card2);
        testPlayer.draw(card3);
        testPlayer.draw(card4);
        testPlayer.draw(card5);
        testPlayer.draw(card6);
        testPlayer.draw(card7);
        testPlayer.draw(card8);
        testPlayer.draw(card9);
        testPlayer.draw(card10);
        testPlayer.draw(card11);
        testPlayer.draw(card12);
        testPlayer.draw(card13);
        List<Card> allDuplicates=testPlayer.getAllDuplicates();
        List<List<Card>> allRows=testPlayer.getAllRows();
        List<List<Card>> allPairs=testPlayer.getAllPairs(allDuplicates);

        boolean isPossible= testPlayer.isPossible(allRows.get(0), allPairs.get(1),allDuplicates);
        boolean isPossible2=testPlayer.isPossible(allRows.get(0), allPairs.get(0), allDuplicates);
        assertThat(isPossible).isFalse();
    }
}