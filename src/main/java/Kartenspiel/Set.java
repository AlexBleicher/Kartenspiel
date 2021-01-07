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
}
