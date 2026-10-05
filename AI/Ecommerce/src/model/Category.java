package model;

public enum Category {
    FOOD("Food"),
    DRINK("Drink"),
    CLOTHES("Clothes"),
    ELECTRONICS("Electronics");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
