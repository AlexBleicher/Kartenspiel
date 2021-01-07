package Kartenspiel;

public class Card {

    public CardColor color;
    public int points;

    public Card(CardColor givenColor, int givenPoints) {
        color = givenColor;
        points = givenPoints;
    }

    public CardColor getColor(){
        return color;
    }

    public int getPoints(){
        return points;
    }


}
