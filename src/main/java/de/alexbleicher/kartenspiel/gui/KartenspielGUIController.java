package de.alexbleicher.kartenspiel.gui;

import de.alexbleicher.kartenspiel.logik.Card;
import de.alexbleicher.kartenspiel.logik.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.GREEN;

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

    private List<Label> playerLabels = new ArrayList<>();

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

    public void startGame(ActionEvent e) {
        lOutput.setText("Das Spiel beginnt");
        game.startGame();
        playerLabels.add(lPlayer1);
        playerLabels.add(lPlayer2);
        playerLabels.add(lPlayer3);
        playerLabels.add(lPlayer4);
        for (int i = 0; i < 4; i++) {
            playerLabels.get(i).setText(game.playerList.get(i).getName());
        }
        showLastDiscardedCard();
        highlightPlayerOnTurn();
    }

    public void showLastDiscardedCard() {
        Card lastDiscardedCard = game.getLastDiscardedCard();
        tfDiscardPile.setText("Letzte abgelegte Karte: " + lastDiscardedCard.getfullName());
    }

    public void highlightPlayerOnTurn() {
        for (Label label : playerLabels) {
            if (label.getText().equals(game.getPlayerOnTurn().getName())) {
                label.setTextFill(GREEN);
                break;
            }
        }

    }
}
