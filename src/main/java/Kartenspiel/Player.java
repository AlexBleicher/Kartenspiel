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
        List<Card> allDuplicates= getAllDuplicates();
        List<List<Card>> allPossiblePairs = getAllPairs(allDuplicates);
        List<List<Card>> allPossibleRows = getAllRows();

        //Finde die höchstmögliche Punktzahl der aktuellen Hand
        Map<List<Card> , Integer > pointMapPair = new HashMap<>();
        Map<List<Card> , Integer > pointMapRow = new HashMap<>();

        for(List<Card> pair: allPossiblePairs){
            for(Card card:pair){
                System.out.println(card.getfullName());
            }
            pointMapPair.put(pair, calculatePoints(pair));
        }
        for(List<Card> row: allPossibleRows){
            for(Card card:row){
                System.out.println(card.getfullName());
            }
            pointMapRow.put(row, calculatePoints(row));
        }

        System.out.println(pointMapPair.entrySet());
        System.out.println(pointMapRow.entrySet());
    }

    public List<Card> getAllDuplicates(){
        List<Card> allDuplicates=new ArrayList<>();
        for(int i=0;i<hand.size();i++){
            Card currentCard=hand.get(i);
            for(int j=i+1;j<hand.size();j++){
                Card comparedCard=hand.get(j);
                if(currentCard.equalsStructural(comparedCard)){
                    allDuplicates.add(currentCard);
                }
            }
        }
        return allDuplicates;
    }
    public Integer calculatePoints(List<Card> cards){
        Integer totalPoints=0;
        for(Card card:cards){
            totalPoints+=card.getPoints();
        }
        return totalPoints;
    }
    public List<List<Card>> getAllPairs(List<Card> allDuplicates) {
        List<List<Card>> allPossiblePairs = new ArrayList<>();

        //Trenne Hand nach Zahlen
        Map<Name, List<Card>> allNamesOfCards = hand.stream()
                .collect(Collectors.groupingBy(card -> card.getName()));
        //Für alle Zahlen mögliche Paare finden (selbe Zahl unterschiedliche Farbe)
        for (Map.Entry<Name, List<Card>> cardNameListEntry : allNamesOfCards.entrySet()) {
            List<Card> currentList = cardNameListEntry.getValue();
            currentList.sort(Comparator.comparing(card -> card.getColor()));
            //Liste ist jetzt nach Farben sortiert
            //Duplikate rauswerfen
            currentList.removeIf(allDuplicates::contains);

            if (currentList.size() >= 3) {
                allPossiblePairs.add(currentList);
            }
        }
        return allPossiblePairs;
    }

    public List<List<Card>> getAllRows() { //Sobald 3 oder Mehr Karten der Gleichen Farbe aufeinander folgen.
        List<List<Card>> allPossibleRows = new ArrayList<>();

        //Trenne Hand nach Farben
        Map<CardColor, List<Card>> allColorsWithCards = hand.stream()
                .collect(Collectors.groupingBy(card -> card.getColor()));

        //Für alle Farben alle möglichen Straßen finden
        for (Map.Entry<CardColor, List<Card>> cardColorListEntry : allColorsWithCards.entrySet()) {
            List<Card> currentList = cardColorListEntry.getValue();
            currentList.sort(Comparator.comparing(card1 -> card1.getName()));
            //Liste ist Sortiert -> Straßen finden
            List<Card> currentRow = new ArrayList<>();
            for (int i = 0; i < currentList.size() - 1; i++) {
                Card currentCard = currentList.get(i);
                Card nextCard = currentList.get(i + 1);
                currentRow.add(currentCard);
                if (nextCard.getName().ordinal() != currentCard.getName().ordinal() + 1) {
                    currentRow = new ArrayList<>();
                } else {
                    if (currentRow.size() >= 3) {
                        allPossibleRows.add(currentRow);
                    }
                }
            }
        }
        return allPossibleRows;
    }
}