package org.example;

public class Player extends Entity {

    private boolean hasWon = false;

    public Player() {
        super();
        setType(1);
    };

    public boolean hasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
}
