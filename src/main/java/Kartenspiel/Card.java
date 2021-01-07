package Kartenspiel;

public class Card {

    public CardColor color;
    public int points;

    public Card(CardColor givenColor, int givenPoints) {
        color = givenColor;
        points = givenPoints;
    }

    public CardColor getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        String name;
        if (points <= 10) {
            String pointsAsString = Integer.toString(points);
            name = pointsAsString + color.toString();
        } else if (points == 11) {
            name = "Jack" + color.toString();
        } else if (points == 12) {
            name = "Lady" + color.toString();
        } else if (points == 13) {
            name = "King" + color.toString();
        } else {
            name = "Ace" + color.toString();
        }
        return name;
    }
}
