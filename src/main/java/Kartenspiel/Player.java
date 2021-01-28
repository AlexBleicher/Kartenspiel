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
    private List<List<Card>> cardsChosen=new ArrayList<>();

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

    public List<List<Card>> getCardsChosen(){
        return cardsChosen;
    }

    public void discard(Card chosenCard) {
        hand.remove(chosenCard);
    }

    public void setPointsTotal(int amountOfPoints){
        pointsTotal+=amountOfPoints;
    }

    public void comeOut(List<List<Card>> cardsUsedToComeOut){

    }

    public Card chooseCard(String action, int index){
        if(action.equals("Discard")){
            return hand.get(index);
        }
        else if(action.equals("Select Pair")){
            addToPair(hand.get(index));

        }
        else if(action.equals("Select Row")){
            addToRow(hand.get(index));
        }
        return new Card(CardColor.HEART,0);
    }

    public void addToPair(Card addedCard){
        boolean added=false;
        for(List<Card> searchPair: cardsChosen){
            if(isPair(searchPair)){
                if(fitsPair(addedCard,searchPair)) {
                    searchPair.add(addedCard);
                    added=true;
                    break;
                }
            }
        }
        if (!added) {
            List<Card> newPair=new ArrayList<>();
            newPair.add(addedCard);
            cardsChosen.add(newPair);
        }
    }

    public boolean isPair(List<Card> checkedCards){
        boolean isPair=false;
        if(checkedCards.size()==1||checkedCards.size()==0){
            isPair=true;
        }
        else if(checkedCards.get(0).getColor()!=checkedCards.get(1).getColor()){
            isPair=true;
        }
        return isPair;
    }
    public boolean fitsPair(Card cardtoProve, List<Card> pairToCheck){
        boolean fitsPair=true;
        if(cardtoProve.getName()!=pairToCheck.get(0).getName()){
            fitsPair=false;
        }
        for(Card card: pairToCheck){
            if (card.equalsStructural(cardtoProve)) {
                fitsPair=false;
            }
        }
        return fitsPair;
    }

    public void addToRow(Card addedCard){
        boolean added=false;
        for(List<Card> row: cardsChosen){
            if(isRow(row)){
                if(fitsRow(addedCard,row)){
                    row.add(addedCard);
                    added=true;
                    break;
                }
            }
        }
        if(!added){
            List<Card> newList=new ArrayList<>();
            newList.add(addedCard);
            cardsChosen.add(newList);
        }
    }
    public boolean isRow(List<Card> rowToCheck){
        if(rowToCheck.size()==1){
            return true;
        }
        for(int i=0;i<rowToCheck.size()-1;i++){
            Card currentCard=rowToCheck.get(i);
            Card nextCard=rowToCheck.get(i+1);
            if(currentCard.getName().ordinal()!=nextCard.getName().ordinal()-1){
                return false;
            }
        }

        return true;
    }
    public boolean fitsRow(Card cardToAdd, List<Card> rowToCheck){
        boolean fitsRow=true;
        for(Card card:rowToCheck){
            if(card.equalsStructural(cardToAdd)){
                fitsRow=false;
            }
        }
        if(cardToAdd.getColor()!=rowToCheck.get(0).getColor()){
            fitsRow=false;
        }
        else if(cardToAdd.getName().ordinal()!=rowToCheck.get(0).getName().ordinal()-1&&cardToAdd.getName().ordinal()!=rowToCheck.get(rowToCheck.size()-1).getName().ordinal()+1){
            fitsRow=false;
        }
        return fitsRow;
    }
    /*public int organizeHand() {
        int currentPoints = 0;
        int maxPossiblePoints = 0;
        List<Card> allDuplicates = getAllDuplicates();
        List<List<Card>> allPossiblePairs = getAllPairs(allDuplicates);
        List<List<Card>> allPossibleRows = getAllRows();

        //Finde die höchstmögliche Punktzahl der aktuellen Hand
        Map<List<Card>, Integer> pointMapPair = new HashMap<>();
        Map<List<Card>, Integer> pointMapRow = new HashMap<>();

        for (List<Card> pair : allPossiblePairs) {
            pointMapPair.put(pair, calculatePoints(pair));
        }
        for (List<Card> row : allPossibleRows) {
            pointMapRow.put(row, calculatePoints(row));
        }

        for (Map.Entry<List<Card>, Integer> pair : pointMapPair.entrySet()) {
            currentPoints = pair.getValue();
            //Alle Paare abgleichen
            for (Map.Entry<List<Card>, Integer> otherPair : pointMapPair.entrySet()) {
                if (!pair.equals(otherPair) && isPossible(pair.getKey(), otherPair.getKey(), allDuplicates)) {
                    currentPoints += otherPair.getValue();
                }
            }
            //Alle Reihen abgleichen
            for (Map.Entry<List<Card>, Integer> otherRow : pointMapRow.entrySet()) {
                if (isPossible(pair.getKey(), otherRow.getKey(), allDuplicates)) {
                    currentPoints += otherRow.getValue();
                }
            }
            //Höchsten Wert ermitteln
            if (currentPoints >= maxPossiblePoints) {
                maxPossiblePoints = currentPoints;
            }
        }
        for (Map.Entry<List<Card>, Integer> row :pointMapRow.entrySet()){
            currentPoints=row.getValue();

            for (Map.Entry<List<Card>, Integer> otherRow : pointMapRow.entrySet()) {
                if (!row.equals(otherRow) && isPossible(row.getKey(), otherRow.getKey(), allDuplicates)) {
                    currentPoints += otherRow.getValue();
                }
            }
            for (Map.Entry<List<Card>, Integer> otherPair : pointMapPair.entrySet()) {
                if (isPossible(row.getKey(), otherPair.getKey(), allDuplicates)) {
                    currentPoints += otherPair.getValue();
                }
            }
            if (currentPoints >= maxPossiblePoints) {
                maxPossiblePoints = currentPoints;
            }
        }
        return maxPossiblePoints;
        //Hand danach sortieren
    }
    //Rekursiver Ansatz: Nimm ein mögliches Paar/eine mögliche Reihe zur Seite, schau dir den Rest an
    public boolean isPossible(List<Card> cardList1, List<Card> cardList2, List<Card> duplicateCards) {
        boolean cardCombiPossible = true;
        for (Card card : cardList1) {
            for (Card compareCard : cardList2) {
                if (card.equalsStructural(compareCard) && !(duplicateCards.contains(card) || duplicateCards.contains(compareCard))) {
                    cardCombiPossible = false;
                }
            }
        }
        return cardCombiPossible;
    }

    public List<Card> getAllDuplicates() {
        List<Card> allDuplicates = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.get(i);
            for (int j = i + 1; j < hand.size(); j++) {
                Card comparedCard = hand.get(j);
                if (currentCard.equalsStructural(comparedCard)) {
                    allDuplicates.add(currentCard);
                }
            }
        }
        return allDuplicates;
    }

    public Integer calculatePoints(List<Card> cards) {
        Integer totalPoints = 0;
        for (Card card : cards) {
            totalPoints += card.getPoints();
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
                if (nextCard.equalsStructural(currentCard) && i < currentList.size() - 2) {
                    nextCard = currentList.get(i + 2);
                }
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
    }*/
}