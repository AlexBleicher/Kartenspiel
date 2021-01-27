package Kartenspiel;

import java.util.*;

public class Game {

    public List<Player> playerList = new ArrayList<>();
    private LinkedList<Card> set = new LinkedList<>();
    private LinkedList<Card> discardPile = new LinkedList<>();

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
        hand_out();
        //playerList.get(0).showHand();
        turn_first_Card();
    }

    public void playGame(Player starter) {
        boolean gameOver = false;
        Player currentPlayer = starter;
        while (!gameOver) {
            if (currentPlayer.getHand().size() == 0) {
                gameOver = true;
            } else {
                draw_a_Card(currentPlayer);
                discard_a_Card(currentPlayer);
                currentPlayer=playerList.get(playerList.indexOf(currentPlayer)+1);
            }
        }
        for(int i=0; i<playerList.size();i++){
            playerList.get(i).setPointsTotal(playerList.get(i).getPointsHand());
        }
    }

    public void playRound() {
        generatePlayers();
        for (int i = 0; i < playerList.size(); i++) {
            startGame();
            playGame(playerList.get(i));
        }
    }

    public void generatePlayers() { //Nur vorübergehend, um Start der Runde zu simulieren
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

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

    public void draw_a_Card(Player player) {
        Card currentCard = set.getFirst();
        set.removeFirst();
        player.draw(currentCard);
    }

    public void discard_a_Card(Player player) {
        Card chosenCard = player.chooseCard("Discard",0);
        player.discard(chosenCard);
        discardPile.add(chosenCard);
    }
}
