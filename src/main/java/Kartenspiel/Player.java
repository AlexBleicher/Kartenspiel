package Kartenspiel;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Player {

    private List<Card> hand = new ArrayList<>();
    private int pointsHand;
    private boolean isOut;
    private int pointsTotal;

    public int getPointsHand() {
        return pointsHand;
    }

    public boolean isOut() {
        return isOut;
    }

    public Player() {
        pointsTotal = 0;
        pointsHand = 0;
        isOut = false;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void showHand() {
        for (Card card : hand) {
            System.out.println(card.getfullName());
        }

    }

    public void draw(Card newCard) {
        hand.add(newCard);
    }

    public int getPointsTotal() {
        return pointsTotal;
    }

    public void discard(Card chosenCard) {
        hand.remove(chosenCard);
    }

    public void organizeHand() {
        int currentPoints = 0;
        int maxPossiblePoints = 0;
        List<List<Card>> allPossiblePairs = getAllPairs();
        //List<List<Card>> allPossibleRows = getAllRows();
    }

    public List<List<Card>> getAllPairs() {

        Map<Name, Set<Card>> allPossibleNames = new HashMap<>();
        for (int i = 0; i < hand.size(); i++) {

        }
        return new ArrayList<>();
    }

    public void getAllRows() { //Sobald 3 oder Mehr Karten der Gleichen Farbe aufeinander folgen.
        //Trenne Hand nach Farben
        Map<CardColor, List<Card>> allColorsWithCards = hand.stream()
                .collect(Collectors.groupingBy(card -> card.getColor()));

        //Für alle Farben alle möglichen Straßen finden
        for (Map.Entry<CardColor, List<Card>> cardColorListEntry : allColorsWithCards.entrySet()) {
            CardColor currentColor = cardColorListEntry.getKey();
            List<Card> currentList = cardColorListEntry.getValue();
            currentList.sort(Comparator.comparing(card1 -> card1.getName()));
            //Liste ist Sortiert -> Straßen finden
            List<Card> currentRow=new ArrayList<>();
            for(int i=0; i<currentList.size()-1;i++){
                Card currentCard=currentList.get(i);
                Card nextCard=currentList.get(i+1);
                currentRow.add(currentCard);
                if(nextCard.getName().ordinal()==currentCard.getName().ordinal()+1 && !currentRow.contains(nextCard)){
                    currentRow.add(nextCard);
                    System.out.println(currentRow.size());
                }
                else{
                    currentRow=new ArrayList<>();
                }
                System.out.println(currentCard.getfullName());
            }
        }



    }
}

