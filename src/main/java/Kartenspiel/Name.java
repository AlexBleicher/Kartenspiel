package Kartenspiel;

public enum Name {
    ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("10"), LADY("10"), KING("10"), ACE("11");

    private String value;

    public String getValue() {
        return this.value;
    }

    private Name(String value) {
        this.value = value;
    }
    }
