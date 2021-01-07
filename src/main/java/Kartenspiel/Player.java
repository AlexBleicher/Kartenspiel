package Kartenspiel;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> hand = new ArrayList<>();

    public void draw(Card newCard){
        hand.add(newCard);
    }

    public void discard(Card chosenCard){
        hand.remove(chosenCard);
    }
}
