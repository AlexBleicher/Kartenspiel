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

    public void startRound() {
        generateCards();
        generateCards();
        Collections.shuffle(set);
        generatePlayers();
        hand_out();
        playerList.get(0).showHand();

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
}
