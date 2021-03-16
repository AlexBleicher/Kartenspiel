package de.alexbleicher.kartenspiel.gui;

import de.alexbleicher.kartenspiel.logik.Card;
import de.alexbleicher.kartenspiel.logik.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.BLACK;
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
    private TextField tfChooseCard;

    private List<TextField> playerFields = new ArrayList<>();
    @FXML
    private TextArea taOutput;

    @FXML
    private Label lPlayer1;
    @FXML
    private Label lPlayer2;
    @FXML
    private Label lPlayer3;
    @FXML
    private Label lPlayer4;

    private List<Label> playerLabels = new ArrayList<>();

    private boolean cardDrawn;
    private boolean cardDiscarded;

    @FXML
    public void initialize() {
        String text = "Noch nicht draußen!";
        playerFields.add(tfPlayer1);
        playerFields.add(tfPlayer2);
        playerFields.add(tfPlayer3);
        playerFields.add(tfPlayer4);
        for (int i = 0; i < 4; i++) {
            playerFields.get(i).setText(text);
        }
    }

    public void drawCard(ActionEvent e) {
        taOutput.setText("Karte gezogen!");
        game.drawACard();
        cardDrawn = true;
    }

    public void showHand(ActionEvent e) {
        taOutput.setText("Karten auf der Hand: ");
        List<Card> hand = game.showHand();
        int i = 0;
        for (Card card : hand) {
            taOutput.setText(taOutput.getText() + card.getfullName() + " " + i + " ; \n");
            i++;
        }
    }

    public void discardCard(ActionEvent e) {
        Card chosenCard = chooseCard();
        if (chosenCard != null) {
            game.discardACard(chosenCard);
            taOutput.setText("Karte abgelegt!");
            cardDiscarded = true;
            showLastDiscardedCard();
        } else {
            taOutput.setText("Fehler! Karte nicht gefunden!");
        }
    }

    public void startGame(ActionEvent e) {
        taOutput.setText("Das Spiel beginnt");
        game.startGame();
        playerLabels.add(lPlayer1);
        playerLabels.add(lPlayer2);
        playerLabels.add(lPlayer3);
        playerLabels.add(lPlayer4);
        for (int i = 0; i < 4; i++) {
            playerLabels.get(i).setText(game.playerList.get(i).getName() + " Punkte: " + game.playerList.get(i).getPointsTotal());
        }
        showLastDiscardedCard();
        highlightPlayerOnTurn();
    }

    public void endTurn() {
        if (cardDrawn && cardDiscarded) {
            game.endTurn();
            taOutput.setText("Karte ziehen");
            highlightPlayerOnTurn();
            cardDrawn = false;
            cardDiscarded = false;
        } else {
            taOutput.setText("Noch nicht alle nötigen Aktionen ausgeführt!");
        }
    }

    public void showLastDiscardedCard() {
        Card lastDiscardedCard = game.getLastDiscardedCard();
        tfDiscardPile.setText(lastDiscardedCard.getfullName());
    }

    public void highlightPlayerOnTurn() {
        for (Label label : playerLabels) {
            if (label.getText().contains(game.getPlayerOnTurn().getName())) {
                label.setTextFill(GREEN);
            } else {
                label.setTextFill(BLACK);
            }
        }

    }

    public Card chooseCard() {
        int index = Integer.parseInt(tfChooseCard.getText());
        List<Card> hand = game.showHand();
        tfChooseCard.setText("");
        return hand.get(index);
    }

    public void sortByPair() {
        game.sortByPair();
        showHand(new ActionEvent());
    }

    public void sortByColor() {
        game.sortByColor();
        showHand(new ActionEvent());
    }

    public void comeOut() {
        int index = game.getPlayerList().indexOf(game.getPlayerOnTurn());
        if (game.canComeOut()) {
            playerFields.get(index).setText("draußen");
        } else {
            taOutput.setText("Damit kannst du nicht rausgehen!");
        }
    }
}
