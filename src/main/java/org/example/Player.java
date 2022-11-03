package org.example;

public class Player extends Entity {

    private boolean hasWon = false;

    public Player() {};

    public void movePlayer(String direction) {
        int[] playerCoordinates = getCoordinates();
        switch(direction) {
            case "l":
                playerCoordinates[1] -= 1;
                break;
            case "r":
                playerCoordinates[1] += 1;
                break;
            case "u":
                playerCoordinates[0] -= 1;
                break;
            case "d":
                playerCoordinates[0] += 1;
                break;
        }
    }

    public boolean hasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
}
