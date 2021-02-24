package de.alexbleicher.kartenspiel.gui;

import de.alexbleicher.kartenspiel.logik.Card;
import de.alexbleicher.kartenspiel.logik.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class KartenspielGUIController {

    private Game game = new Game();
    @FXML
    private TextField tfPlayer1;
    @FXML
    private TextField tfPlayer2;
    @FXML
    private TextField tfPlayer3;
    @FXML
    private TextField tfPlayer4;
    @FXML
    private TextField tfDiscardPile;

    @FXML
    private Label lOutput;
    @FXML
    private Label lPlayer1;
    @FXML
    private Label lPlayer2;
    @FXML
    private Label lPlayer3;
    @FXML
    private Label lPlayer4;

    @FXML
    public void initialize() {
        String text = "Noch nicht draußen!";
        tfPlayer1.setText(text);
        tfPlayer2.setText(text);
        tfPlayer3.setText(text);
        tfPlayer4.setText(text);
    }

    public void drawCard(ActionEvent e) {
        lOutput.setText("Karte gezogen!");
    }

    public void showHand(ActionEvent e) {
        lOutput.setText("Karten auf der Hand: ");
    }

    public void discardCard(ActionEvent e) {
        lOutput.setText("Karte abgelegt!");
    }

    public void startGame(ActionEvent e){
        lOutput.setText("Das Spiel beginnt");
        game.startGame();
        lPlayer1.setText(game.playerList.get(0).getName());
        lPlayer2.setText(game.playerList.get(1).getName());
        lPlayer3.setText(game.playerList.get(2).getName());
        lPlayer4.setText(game.playerList.get(3).getName());
        showLastDiscardedCard();
    }

    public void showLastDiscardedCard(){
        Card lastDiscardedCard = game.getLastDiscardedCard();
        tfDiscardPile.setText("Letzte abgelegte Karte: " + lastDiscardedCard.getfullName());
    }
}
