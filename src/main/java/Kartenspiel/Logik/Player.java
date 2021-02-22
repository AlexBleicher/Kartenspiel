package Kartenspiel.Logik;

import java.util.*;


public class Player {

    private List<Card> hand = new ArrayList<>();
    private int pointsHand;
    private boolean isOut;
    private int pointsTotal;
    private List<List<Card>> cardsChosen = new ArrayList<>();
    public List<List<Card>> cardsOnTable = new ArrayList<>();

    public int getPointsHand() {
        return pointsHand;
    }

    public boolean getIsOut() {
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
            System.out.println(card.getfullName() + ": " + hand.indexOf(card));
        }

    }

    public void action() {
        Scanner scann = new Scanner(System.in);
        boolean hasDiscarded=false;
        int index=-1;
        showHand();
        while(!hasDiscarded) {
            System.out.println("Geben Sie hier Ihre gewünschte Aktion (Karte_ablegen, Karte_sortieren, Rausgehen, Anlegen)  ein: ");
            String userInput = scann.next();
            System.out.println(userInput);
            switch (userInput) {
                case ("Karte_ablegen"):
                    System.out.println("Welche Karte soll abgelegt werden? Sie haben folgende Karten auf der Hand: ");
                    showHand();
                    System.out.println("Wählen Sie eine Karte");
                    userInput = scann.next();
                    index = Integer.parseInt(userInput);
                    if (index >= 0) {
                        chooseCard("Discard", index);
                        hasDiscarded=true;
                    }
                    break;
                case("Karte_sortieren"):
                    System.out.println("Welche Karte möchten sie sortieren? Sie haben folgende Karten auf der Hand: ");
                    showHand();
                    System.out.println("Wählen Sie eine Karte");
                    userInput = scann.next();
                    index=Integer.parseInt(userInput);
                    if(index>=0){
                        System.out.println("Wie möchten sie die Karte sortieren? Zu einem Paar, oder zu einer Reihe?");
                        userInput=scann.next();
                        if(userInput.equals("Paar")){
                            chooseCard("Select Pair", index);
                        }
                        else if(userInput.equals("Reihe")){
                            chooseCard("Select Row", index);
                        }
                        else{
                            System.out.println("Keine gültige Eingabe!");
                        }
                    }
                    break;
                case("Rausgehen"):
                    List<List<Card>> cardsChosenToComeOut=new ArrayList<>();
                    boolean finished=false;
                    while(!finished){
                        System.out.println("Wählen sie das Paar oder die Reihe mithilfe des Index. Wenn sie fertig sind geben Sie 'Abbrechen' ein. Sie haben folgende Auswahlmöglichkeiten:" );
                        for(List<Card> cardList: cardsChosenToComeOut){
                            System.out.println("Neues Paar oder neue Reihe :");
                            for(Card card: cardList){
                                System.out.println(card.getfullName());
                            }
                        }
                        userInput=scann.nextLine();
                        if(userInput.equals("Abbrechen")){
                            finished=true;
                        }
                        else {
                            index = Integer.parseInt(userInput);
                            if (index >= 0 && index < cardsChosen.size()) {
                                List<Card> chosenList = cardsChosen.get(index);
                                if (chosenList.size() >= 3) {
                                    cardsChosenToComeOut.add(chosenList);
                                }
                            }
                        }
                    }
                    comeOut(cardsChosenToComeOut);
                    break;
                case("Anlegen"):
                    System.out.println("Welche Karte möchten Sie anlegen? Sie haben folgende Karten auf der Hand: ");
                    showHand();
                    System.out.println("Wählen Sie eine Karte: ");
                    userInput=scann.nextLine();
                    index=Integer.parseInt(userInput);
                    addCardToTable(hand.get(index));
                    break;
                default:
                    System.out.println("Eingabe nicht möglich!");
            }
        }
    }

    public void showCardsOnTable() {
        for (List<Card> list : cardsOnTable) {
            System.out.println("Neues zusammenhängedes Konstrukt: ");
            for (Card card : list) {
                System.out.println(card.getfullName());
            }
        }
    }

    public void draw(Card newCard) {
        hand.add(newCard);
    }

    public int getPointsTotal() {
        return pointsTotal;
    }

    public List<List<Card>> getCardsChosen() {
        return cardsChosen;
    }

    public void discard(Card chosenCard) {
        hand.remove(chosenCard);
    }

    public void setPointsTotal(int amountOfPoints) {
        pointsTotal += amountOfPoints;
    }

    public void comeOut(List<List<Card>> cardsUsedToComeOut) {
        int points = 0;
        for (List<Card> chosenList : cardsUsedToComeOut) {
            points += calculatePoints(chosenList);
        }
        if (points >= 40) {
            for (List<Card> currentList : cardsUsedToComeOut) {
                for (Card cardToRemove : currentList) {
                    hand.remove(cardToRemove);
                }
                cardsOnTable.add(currentList);
            }
            isOut = true;
        }
    }

    public void addCardToTable(Card chosenCard) {
        boolean added = false;
        for (List<Card> possibleAddList : cardsOnTable) {
            if (isPair(possibleAddList)) {
                if (fitsPair(chosenCard, possibleAddList)) {
                    possibleAddList.add(chosenCard);
                    hand.remove(chosenCard);
                    added = true;
                    break;
                }
            } else if (isRow(possibleAddList)) {
                if (fitsRow(chosenCard, possibleAddList)) {
                    possibleAddList.add(chosenCard);
                    hand.remove(chosenCard);
                    added = true;
                    break;
                }
            }
        }
        if (!added) {
            System.out.println("Karte kann nicht an eigene Karten angefügt werden!");
        }
    }

    public Card chooseCard(String action, int index) {
        if (action.equals("Discard")) {
            discard(hand.get(index));
        } else if (action.equals("Select Pair")) {
            addToPair(hand.get(index));

        } else if (action.equals("Select Row")) {
            addToRow(hand.get(index));
        }
        return new Card(CardColor.HEART, 0);
    }

    public void addToPair(Card addedCard) {
        boolean added = false;
        for (List<Card> searchPair : cardsChosen) {
            if (isPair(searchPair)) {
                if (fitsPair(addedCard, searchPair)) {
                    searchPair.add(addedCard);
                    added = true;
                    break;
                }
            }
        }
        if (!added) {
            List<Card> newPair = new ArrayList<>();
            newPair.add(addedCard);
            cardsChosen.add(newPair);
        }
    }

    public boolean isPair(List<Card> checkedCards) {
        boolean isPair = false;
        if (checkedCards.size() == 1 || checkedCards.size() == 0) {
            isPair = true;
        } else if (checkedCards.get(0).getColor() != checkedCards.get(1).getColor()) {
            isPair = true;
        }
        return isPair;
    }

    public boolean fitsPair(Card cardtoProve, List<Card> pairToCheck) {
        boolean fitsPair = true;
        if (cardtoProve.getName() != pairToCheck.get(0).getName()) {
            fitsPair = false;
        }
        for (Card card : pairToCheck) {
            if (card.equalsStructural(cardtoProve)) {
                fitsPair = false;
            }
        }
        return fitsPair;
    }

    public void addToRow(Card addedCard) {
        boolean added = false;
        for (List<Card> row : cardsChosen) {
            if (isRow(row)) {
                if (fitsRow(addedCard, row)) {
                    row.add(addedCard);
                    added = true;
                    break;
                }
            }
        }
        if (!added) {
            List<Card> newList = new ArrayList<>();
            newList.add(addedCard);
            cardsChosen.add(newList);
        }
    }

    public boolean isRow(List<Card> rowToCheck) {
        if (rowToCheck.size() == 1) {
            return true;
        }
        for (int i = 0; i < rowToCheck.size() - 1; i++) {
            Card currentCard = rowToCheck.get(i);
            Card nextCard = rowToCheck.get(i + 1);
            if (currentCard.getName().ordinal() != nextCard.getName().ordinal() - 1) {
                return false;
            }
        }

        return true;
    }

    public boolean fitsRow(Card cardToAdd, List<Card> rowToCheck) {
        boolean fitsRow = true;
        for (Card card : rowToCheck) {
            if (card.equalsStructural(cardToAdd)) {
                fitsRow = false;
            }
        }
        if (cardToAdd.getColor() != rowToCheck.get(0).getColor()) {
            fitsRow = false;
        } else if (cardToAdd.getName().ordinal() != rowToCheck.get(0).getName().ordinal() - 1 && cardToAdd.getName().ordinal() != rowToCheck.get(rowToCheck.size() - 1).getName().ordinal() + 1) {
            fitsRow = false;
        }
        return fitsRow;
    }

    public Integer calculatePoints(List<Card> cards) {
        Integer totalPoints = 0;
        for (Card card : cards) {
            totalPoints += card.getPoints();
        }
        return totalPoints;
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