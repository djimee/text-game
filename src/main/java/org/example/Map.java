package org.example;

import static java.util.Arrays.fill;

public class Map {

    private int SIZE;
    private int[][] grid = new int[SIZE][SIZE];

    public Map(int size) {
        this.SIZE = size;
        initialiseGrid();

//        this.playerCoordinates = Utils.getRandomCoordinates();

//        int[] temp = Utils.getRandomCoordinates();
//
//        // generate new coordinates until they stop overlapping
//        while(temp == this.playerCoordinates) {
//            temp = Utils.getRandomCoordinates();
//        }
//        this.treasureCoordinates = temp;
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void initialiseGrid() {
        int[][] grid = new int[SIZE][SIZE];
        for (int[] row : grid) {
            fill(row, 0);
        }
        setGrid(grid);
    }

    public boolean checkValidMovement(Player player, String direction) {
        boolean check = false;
        int[] playerCoordinates = player.getCoordinates();

        switch(direction) {
            case "l":
                check = (playerCoordinates[1] - 1) >= 0;
                if(!check)
                    System.out.println("You cannot move further left. You are at the edge.\n");
                break;
            case "r":
                check = (playerCoordinates[1] + 1) < SIZE;
                if(!check)
                    System.out.println("You cannot move further right. You are at the edge.\n");
                break;
            case "u":
                check = (playerCoordinates[0] - 1) >= 0;
                if(!check)
                    System.out.println("You cannot move further up. You are at the edge.\n");
                break;
            case "d":
                check = (playerCoordinates[0] + 1) < SIZE;
                if(!check)
                    System.out.println("You cannot move further down. You are at the edge.\n");
                break;
            default:
                System.out.println("\nPlease choose:"
                        + "\n'l' for going left,"
                        + "\n'r' for going right,"
                        + "\n'u' for going up,"
                        + "\n'd' for going down."
                        + "\n");
        }
        return check;
    }

    public void displayGrid() {
        int[][] grid = getGrid();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                int curr = this.grid[y][x];
                if (curr == 0) {
                    System.out.print("O ");
                } else if (curr == 1) {
                    System.out.print("X ");
                } else if (curr == 2) {
                    System.out.print("T ");
                } else if (curr == -1) {
                    System.out.print("M ");
                }
            }
            System.out.println("");
        }
    }
    public boolean checkMonster(Monster[] monster, int y, int x) {
        for(Monster m : monster) {
            int[] temp = m.getCoordinates();
            if(y == temp[0] && x == temp[1])
                return true;
        }
        return false;
    }
}

//    public void displayGrid(Player players, boolean treasure) {
//        int[] playerCoordinates = this.getPlayerCoordinates();
//
//        if (!treasure) {
//            for (int i = 0; i)
//            for(int y = 0; y < SIZE; y++) {
//                for(int x = 0; x < SIZE; x++) {
//                    if(y == playerCoordinates[0] && x == playerCoordinates[1])
//                        System.out.print("X ");
//                    else
//                        System.out.print("O ");
//                }
//                System.out.print("\n");
//            }
//        }
//        else {
//            for(int y = 0; y < SIZE; y++) {
//                for(int x = 0; x < SIZE; x++) {
//                    if(y == playerCoordinates[0] && x == playerCoordinates[1] && treasure)
//                        System.out.print("W ");
//                    else if(y == playerCoordinates[0] && x == playerCoordinates[1])
//                        System.out.print("X ");
//                    else
//                        System.out.print("O ");
//                }
//                System.out.print("\n");
//            }
//        }
//    }

//    public void displayGrid(Monster[] monster) {
//        for(int y = 0; y < SIZE; y++) {
//            for(int x = 0; x < SIZE; x++) {
//                if(y == treasureCoordinates[0] && x == treasureCoordinates[1])
//                    System.out.print("W ");
//                else if(checkMonster(monster, y, x)) {
//                    System.out.print("M ");
//                }
//                else
//                    System.out.print("O ");
//            }
//            System.out.print("\n");
//        }
//    }

