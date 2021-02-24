package de.alexbleicher.kartenspiel;

import de.alexbleicher.kartenspiel.logik.Card;
import de.alexbleicher.kartenspiel.logik.CardColor;
import de.alexbleicher.kartenspiel.logik.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    public void testIsPair() {
        Player testPlayer = new Player("Hans");
        Card card1 = new Card(CardColor.HEART, 1);
        Card card2 = new Card(CardColor.SPADES, 1);
        Card card3 = new Card(CardColor.CROSS, 1);
        Card card4 = new Card(CardColor.HEART, 2);
        Card card5 = new Card(CardColor.HEART, 2);
        Card card6 = new Card(CardColor.CHECK, 3);
        Card card7 = new Card(CardColor.CHECK, 4);
        Card card8 = new Card(CardColor.CHECK, 5);
        List<Card> listCardsPair = new ArrayList<>();
        listCardsPair.add(card1);
        listCardsPair.add(card2);
        listCardsPair.add(card3);

        List<Card> listCardDuplicate = new ArrayList<>();
        listCardDuplicate.add(card4);
        listCardDuplicate.add(card5);

        List<Card> listCardRow = new ArrayList<>();
        listCardRow.add(card6);
        listCardRow.add(card7);
        listCardRow.add(card8);

        boolean isPair = testPlayer.isPair(listCardsPair);
        boolean isNoPair = testPlayer.isPair(listCardRow);
        //assertThat(isPair).isTrue();
        assertThat(isNoPair).isFalse();

    }

    @Test
    void testAddToPair() {
        Player testPlayer = new Player("Hans");
        Card card1 = new Card(CardColor.HEART, 1);
        Card card2 = new Card(CardColor.CHECK, 2);
        Card card3 = new Card(CardColor.SPADES, 2);
        Card card4 = new Card(CardColor.CROSS, 2);
        Card card5 = new Card(CardColor.CHECK, 1);
        Card card6 = new Card(CardColor.CROSS, 1);
        testPlayer.addToPair(card1);
        testPlayer.addToPair(card2);
        testPlayer.addToPair(card3);
        testPlayer.addToPair(card4);
        testPlayer.addToPair(card5);
        testPlayer.addToPair(card6);
        for (List<Card> pairs : testPlayer.getCardsChosen()) {
            for (Card card : pairs) {
                System.out.println(card.getfullName());
            }
        }
        assertThat(testPlayer.getCardsChosen().size()).isEqualTo(2);

    }
    @Test
    public void isRow(){
        Player testPlayer=new Player("Hans");
        Card card1=new Card(CardColor.HEART,1);
        Card card2=new Card(CardColor.HEART,2);
        Card card3=new Card(CardColor.HEART,3);
        List<Card> testRow=new ArrayList<>();
        testRow.add(card1);
        testRow.add(card2);
        testRow.add(card3);

        boolean isRow=testPlayer.isRow(testRow);
        assertThat(isRow).isTrue();
    }
    @Test
    public void testFitsRow() {
        Player testPlayer = new Player("Hans");
        Card card1 = new Card(CardColor.HEART, 1);
        Card card2 = new Card(CardColor.HEART, 2);
        List<Card> compareRow = new ArrayList<>();
        compareRow.add(card1);
        compareRow.add(card2);

        Card card3 = new Card(CardColor.HEART, 4);
        boolean doesFit = testPlayer.fitsRow(card3, compareRow);
        assertThat(doesFit).isFalse();
    }

    @Test
    public void testChooseCard(){
        Player testPlayer=new Player("Hans");

        Card heartTwo=new Card(CardColor.HEART, 1);
        Card heartThree=new Card(CardColor.HEART,2);
        Card crossTwo=new Card(CardColor.CROSS,1);
        Card disposableCard=new Card(CardColor.SPADES, 10);

        List<Card> drawList=new ArrayList<>();
        drawList.add(heartTwo);
        drawList.add(heartThree);
        drawList.add(crossTwo);
        drawList.add(disposableCard);

        for(Card card:drawList){
            testPlayer.draw(card);
        }

        testPlayer.chooseCard("Select Row", 0);
        testPlayer.chooseCard("Select Row", 1);
        testPlayer.chooseCard("Discard",3);

        assertThat(testPlayer.getHand().size()).isEqualTo(3);
    }

    @Test
    public void testComeOut(){
        Player testPlayer=new Player("Hans");
        List<List<Card>> testList=new ArrayList<>();
        List<Card> testPair=new ArrayList<>();
        List<Card> testRow=new ArrayList<>();
        Card card1=new Card(CardColor.HEART, 10);
        Card card2=new Card(CardColor.CHECK, 10);
        Card card3=new Card(CardColor.SPADES, 10);
        Card card4=new Card(CardColor.CROSS, 10);
        Card card5=new Card(CardColor.HEART,1);
        Card card6=new Card(CardColor.HEART,2);
        Card card7=new Card(CardColor.HEART,3);
        testPair.add(card1);
        testPair.add(card2);
        testPair.add(card3);
        testPair.add(card4);
        testList.add(testPair);
        testRow.add(card5);
        testRow.add(card6);
        testRow.add(card7);
        testList.add(testRow);
        testPlayer.comeOut(testList);
        boolean isOut=testPlayer.getIsOut();
        testPlayer.showCardsOnTable();
        assertThat(isOut).isTrue();
    }
    @Test
    public void testAddToTable(){
        Player testPlayer=new Player("Hans");
        List<List<Card>> testList=new ArrayList<>();
        List<Card> testPair=new ArrayList<>();
        List<Card> testRow=new ArrayList<>();
        Card card1=new Card(CardColor.HEART, 10);
        Card card2=new Card(CardColor.CHECK, 10);
        Card card3=new Card(CardColor.SPADES, 10);
        Card card4=new Card(CardColor.CROSS, 10);
        Card card5=new Card(CardColor.HEART,1);
        Card card6=new Card(CardColor.HEART,2);
        Card card7=new Card(CardColor.HEART,3);
        testPair.add(card1);
        testPair.add(card2);
        testPair.add(card3);
        testPair.add(card4);
        testList.add(testPair);
        testRow.add(card5);
        testRow.add(card6);
        testRow.add(card7);
        testList.add(testRow);
        testPlayer.comeOut(testList);
        testPlayer.showCardsOnTable();
        testPlayer.addCardToTable(new Card(CardColor.HEART, 4));
        System.out.println("Karte hinzugefügt");
        testPlayer.showCardsOnTable();
        assertThat(testPlayer.cardsOnTable.get(1).size()).isEqualTo(4);
    }

   /* @Test
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

        Card card1=new Card(CardColor.HEART, 1);
        Card card2=new Card(CardColor.HEART, 2);
        Card card3=new Card(CardColor.HEART, 3);
        Card card5=new Card(CardColor.HEART, 1);
        Card card6=new Card(CardColor.HEART, 2);
        Card card7=new Card(CardColor.HEART, 3);
        Card card8=new Card(CardColor.SPADES, 3);
        Card card9=new Card(CardColor.CHECK, 3);

        testPlayer.draw(card1);
        testPlayer.draw(card2);
        testPlayer.draw(card3);
        testPlayer.draw(card5);
        testPlayer.draw(card6);
        testPlayer.draw(card7);
        testPlayer.draw(card8);
        testPlayer.draw(card9);
        assertThat(testPlayer.organizeHand()).isEqualTo(21);
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
    }*/
}