package Kartenspiel;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Player> playerList = new ArrayList<>();
    private Set set = new Set(this);
    private DiscardPile discardPile = new DiscardPile();

    public List<Player> getPlayerList() {
        return playerList;
    }

    public Set getSet() {
        return set;
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public void startRound() {
        set.generateCards();
        set.mix();
        set.giveCards(playerList.size());


    }

    public void generatePlayers(){ //Nur vorübergehend, um Start der Runde zu simulieren
        Player player1=new Player();
        Player player2=new Player();
        Player player3=new Player();
        Player player4=new Player();

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
    }
}
