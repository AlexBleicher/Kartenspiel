package Kartenspiel;

import java.util.ArrayList;
import java.util.List;

public class Set {

    private List<Card> cards = new ArrayList<>();

    public void generateCards() {
        for (int i = 0; i < CardColor.values().length; i++) {
            for (int j = 1; j <= 13; j++) {
                cards.add(new Card(CardColor.values()[i], j));
            }
        }
    }


    public void mix() {
        int max = cards.size() - 1;
        int min = 0;
        int range = max - min + 1;
        for (int i = 0; i < 100000; i++) {
            int index1 = (int) (Math.random() * range) + min;
            int index2 = (int) (Math.random() * range) + min;

            Card card1 = cards.get(index1);
            Card card2 = cards.get(index2);
            cards.set(index1, card2);
            cards.set(index2, card1);
        }
    }
}
