package Kartenspiel;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Player> playerList = new ArrayList<>();
    private Set set = new Set();
    private DiscardPile discardPile = new DiscardPile();

    public void startRound() {
        set.generateCards();


    }
}
