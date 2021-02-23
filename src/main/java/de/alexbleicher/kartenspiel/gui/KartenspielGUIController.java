package de.alexbleicher.kartenspiel.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class KartenspielGUIController {

    //private Game game;
    @FXML
    private TextField tfPlayer1;
    @FXML
    private TextField tfPlayer2;
    @FXML
    private TextField tfPlayer3;
    @FXML
    private TextField tfPlayer4;

    @FXML
    private Label lOutput;

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
}
