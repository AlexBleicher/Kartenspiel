package Kartenspiel.GUI;

import Kartenspiel.Logik.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class KartenspielGUIController {

    private Game game;
    private TextArea taOutput;

    public void drawCard(ActionEvent e){
        game.drawACard();
        taOutput.setText("Karte gezogen");
    }

    public void showHand(ActionEvent e){
        game.showHand();
    }

    public void discardCard(ActionEvent e){
        game.discardACard();
        taOutput.setText("Karte abgelegt!");
    }
}
