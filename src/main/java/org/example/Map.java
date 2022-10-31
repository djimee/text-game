package org.example;

import java.util.Scanner;

public class Map {

    private int SIZE;

    private int[] playerCoordinates = new int[2];
    private int[] treasureCoordinates = new int[2];

    //    public Map() {};

    public Map(int size) {
        this.SIZE = size;
        this.playerCoordinates = Utils.getRandomCoordinates();

        int[] temp = Utils.getRandomCoordinates();

        // generate new coordinates until they stop overlapping
        while (temp == this.playerCoordinates) {
            temp = Utils.getRandomCoordinates();
        }
        this.treasureCoordinates = temp;
    }

    public int[] getPlayerCoordinates() {
        return playerCoordinates;
    }

    public void setPlayerCoordinates(int[] playerCoordinates) {
        this.playerCoordinates = playerCoordinates;
    }

    public int[] getTreasureCoordinates() {
        return treasureCoordinates;
    }

    public void setTreasureCoordinates(int[] treasureCoordinates) {
        this.treasureCoordinates = treasureCoordinates;
    }


    /**
     * Gets displacement to the treasure
     * @returns double type displacement
     */
    public double getDistanceToTreasure() {
        int y = this.getPlayerCoordinates()[0] - this.getTreasureCoordinates()[0];
        int x = this.getPlayerCoordinates()[1] - this.getTreasureCoordinates()[1];
        return Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
    }

    public void movePlayer(String direction) {
        int[] playerCoordinates = getPlayerCoordinates();
        switch (direction) {
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

    public boolean checkValidMovement(String direction) {
        boolean check = false;

        switch (direction) {
            case "l":
                check = (playerCoordinates[1] - 1) >= 0;
                if (!check)
                    System.out.println("You cannot move further left. You are at the edge.\n");
                break;
            case "r":
                check = (playerCoordinates[1] + 1) < SIZE;
                if (!check)
                    System.out.println("You cannot move further right. You are at the edge.\n");
                break;
            case "u":
                check = (playerCoordinates[0] - 1) >= 0;
                if (!check)
                    System.out.println("You cannot move further up. You are at the edge.\n");
                break;
            case "d":
                check = (playerCoordinates[0] + 1) < SIZE;
                if (!check)
                    System.out.println("You cannot move further down. You are at the edge.\n");
                break;
            default:
                System.out.println("\nPlease choose:" +
                        "\n'l' for going left," +
                        "\n'r' for going right," +
                        "\n'u' for going up," +
                        "\n'd' for going down." +
                        "\n");
        }
        return check;
    }

    public boolean isTreasureFound() {
        int[] treasureCoordinates = getTreasureCoordinates();
        int[] playerCoordinates = getPlayerCoordinates();
        if (treasureCoordinates[0] == playerCoordinates[0] && treasureCoordinates[1] == playerCoordinates[1]) {
            return true;
        }
        return false;
    }

    public void displayGrid(boolean treasure) {
        int[] playerCoordinates = this.getPlayerCoordinates();

        if (!treasure) {
            for (int y = 0; y < SIZE; y++) {
                for (int x = 0; x < SIZE; x++) {
                    if (y == playerCoordinates[0] && x == playerCoordinates[1])
                        System.out.print("X ");
                    else
                        System.out.print("O ");
                }
                System.out.print("\n");
            }
        } else {
            for (int y = 0; y < SIZE; y++) {
                for (int x = 0; x < SIZE; x++) {
                    if (y == playerCoordinates[0] && x == playerCoordinates[1] && treasure)
                        System.out.print("W ");
                    else if (y == playerCoordinates[0] && x == playerCoordinates[1])
                        System.out.print("X ");
                    else
                        System.out.print("O ");
                }
                System.out.print("\n");
            }
        }
    }

    public void displayGrid(Monster[] monster) {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (y == treasureCoordinates[0] && x == treasureCoordinates[1])
                    System.out.print("W ");
                else if (checkMonster(monster, y, x)) {
                    System.out.print("M ");
                } else
                    System.out.print("O ");
            }
            System.out.print("\n");
        }
    }

    public boolean checkMonster(Monster[] monster, int y, int x) {
        for (Monster m: monster) {
            int[] temp = m.getMonsterCoordinates();
            if (y == temp[0] && x == temp[1])
                return true;
        }
        return false;
    }
}
