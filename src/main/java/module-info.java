module Kartenspiel {
    requires javafx.controls;
    requires javafx.fxml;
    exports de.alexbleicher.kartenspiel.gui;
    opens de.alexbleicher.kartenspiel.gui to javafx.fxml;
}