package de.alexbleicher.kartenspiel.logik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Game {

    public List<Player> playerList = new ArrayList<>();
    private LinkedList<Card> set = new LinkedList<>();
    private LinkedList<Card> discardPile = new LinkedList<>();
    private Player playerOnTurn;

    public List<Player> getPlayerList() {
        return playerList;
    }

    public LinkedList<Card> getSet() {
        return set;
    }

    public LinkedList<Card> getDiscardPile() {
        return discardPile;
    }

    public void startGame() {
        generateCards();
        generateCards();
        Collections.shuffle(set);
        generatePlayers();
        hand_out();
        playerOnTurn = playerList.get((int) (Math.random() * playerList.size()));
        //playerList.get(0).showHand();
        turn_first_Card();
    }

    /*public void playGame(Player starter) {
        boolean gameOver = false;
        Player currentPlayer = starter;
        while (!gameOver) {
            if (currentPlayer.getHand().size() == 0) {
                gameOver = true;
            } else {
                drawACard();
                currentPlayer.action();
                currentPlayer=playerList.get(playerList.indexOf(currentPlayer)+1);
            }
        }
        for(int i=0; i<playerList.size();i++){
            playerList.get(i).setPointsTotal(playerList.get(i).getPointsHand());
        }
    }

    public void playRound() {

        for (int i = 0; i < playerList.size(); i++) {
            startGame();
            playGame(playerList.get(i));
        }
    }*/

    public void generatePlayers() { //Nur vorübergehend, um Start der Runde zu simulieren
        Player player1 = new Player("Hans");
        Player player2 = new Player("Dieter");
        Player player3 = new Player("Klaus");
        Player player4 = new Player("Fritz");

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
    }

    public void generateCards() {
        for (int i = 0; i < CardColor.values().length; i++) {
            for (int j = 0; j <= 13; j++) {
                set.add(new Card(CardColor.values()[i], j));
            }
        }
    }

    public void hand_out() {
        for (Player player : playerList) {
            for (int j = 0; j < 13; j++) {
                Card currentCard = set.getFirst();
                set.removeFirst();
                player.draw(currentCard);
            }
        }
    }

    public void turn_first_Card() {
        Card firstCard = set.getFirst();
        set.removeFirst();
        discardPile.add(firstCard);
    }

    public void drawACard() {
        Card currentCard = set.getFirst();
        set.removeFirst();
        playerOnTurn.draw(currentCard);
    }

    public void discardACard(Card chosenCard) {
        playerOnTurn.discard(chosenCard);
        discardPile.add(chosenCard);
    }

    public List<Card> showHand() {
        return playerOnTurn.showHand();
    }

    public Card getLastDiscardedCard() {
        return discardPile.get(discardPile.size() - 1);
    }

    public Player getPlayerOnTurn() {
        return playerOnTurn;
    }

    public void endTurn() {
        if (isLastInRound(playerOnTurn)) {
            playerOnTurn = playerList.get(0);
        } else {
            playerOnTurn = playerList.get(playerList.indexOf(playerOnTurn) + 1);
        }
    }

    public boolean isLastInRound(Player player) {
        return (playerList.indexOf(player) == (playerList.size() - 1));
    }

    public void sortByPair() {
        playerOnTurn.sortByPair();
    }

    public void sortByColor() {
        playerOnTurn.sortByColor();
    }
}

