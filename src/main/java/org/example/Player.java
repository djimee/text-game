package org.example;

public class Player {
    public int[] playerCoordinates = new int[2];

    public Player(int[] playerCoordinates) {
        this.playerCoordinates = playerCoordinates;
    }

    public int[] getPlayerCoordinates() {
        return playerCoordinates;
    }

    public void setPlayerCoordinates(int[] playerCoordinates) {
        this.playerCoordinates = playerCoordinates;
    }
}
