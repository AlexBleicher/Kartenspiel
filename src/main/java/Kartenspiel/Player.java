package Kartenspiel;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> hand = new ArrayList<>();
    private int pointsTotal;

    public List<Card> getHand() {
        return hand;
    }

    public void draw(Card newCard){
        hand.add(newCard);
    }

    public int getPointsTotal() {
        return pointsTotal;
    }

    public void discard(Card chosenCard){
        hand.remove(chosenCard);
    }
}
