package org.example;

import static java.util.Arrays.fill;

public class Map {

    private int SIZE;
    private int[][] grid = new int[SIZE][SIZE];

    public Map(int size) {
        this.SIZE = size;
        initialiseGrid();
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

    public void displayGrid() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                int curr = this.grid[y][x];
                if (curr == 1)
                    System.out.print("X  ");
                else
                    System.out.print("O  ");
            }
            System.out.println("");
        }
    }

    public void displayGridLose() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                int curr = this.grid[y][x];
                // empty space
                if (curr == 0) {
                    System.out.print("O  ");
                // player
                } else if (curr == 2) {
                // treasure
                    System.out.print("T  ");
                } else {
                // monster
                    System.out.print("D  ");
                }
            }
            System.out.println("");
        }
    }

    public void displayGridWin() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                int curr = this.grid[y][x];
                // empty space
                if (curr == 0) {
                    System.out.print("O  ");
                // player
                } else if (curr == 2) {
                // treasure
                    System.out.print("W  ");
                } else if (curr == -1) {
                // monster
                    System.out.print("M  ");
                }
            }
            System.out.println("");
        }
    }
}
