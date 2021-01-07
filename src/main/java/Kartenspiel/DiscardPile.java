package Kartenspiel;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {

    private List<Card> discardedCards = new ArrayList<>();

    public void addCardOnPile(Card addedCard){
        discardedCards.add(addedCard);
    }
}
