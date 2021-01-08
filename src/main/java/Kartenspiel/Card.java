package Kartenspiel;

public class Card {

    public CardColor color;
    public int points;
    public Name name;

    public Card(CardColor givenColor, int index) {
        color = givenColor;
        name = Name.values()[index];
        points = Integer.parseInt(name.getValue());
    }

    public CardColor getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }

    public Name getName() {
        return name;
    }

    public String getfullName() {
        return color + " " + name;
    }
}
