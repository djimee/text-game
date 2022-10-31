package org.example;

import java.util.Scanner;


public class App {
    public static int SIZE = 5;
    public static int NO_OF_MONSTERS = 2;
    public static int numOfMoves = 0;

    public static void main( String[] args ) {
        Map map = new Map(SIZE);
        boolean playerAlive = true;
        boolean hasWon = false;
        Scanner s = new Scanner(System.in);

        Monster[] monster = new Monster[NO_OF_MONSTERS];

        for(int i = 0; i < NO_OF_MONSTERS; i++) {
            monster[i] = new Monster();
            while(monster[i].getMonsterCoordinates() == map.getPlayerCoordinates() || 
                    monster[i].getMonsterCoordinates() == map.getTreasureCoordinates())
                monster[i] = new Monster();
        }

        while (playerAlive && !hasWon) {
            map.displayGrid(false);
            System.out.println("Please enter the direction 'l r u d': ");
            String direction = (s.nextLine()).toLowerCase();
            s = new Scanner(System.in);

            if (map.checkValidMovement(direction)) {
                map.movePlayer(direction);
                System.out.println("Treasure is " + map.getDistanceToTreasure() + "m away from you.\n");
                for(Monster m : monster) {
                    if(landedOnMonster(m, map)) {
                        playerAlive = false;
                        break;
                    }
                }
                hasWon = map.isTreasureFound();
            }
            numOfMoves += 1;
        }

        if(hasWon) {
            map.displayGrid(true);
            System.out.println("My G, You have found the treasure in " + numOfMoves + " moves... SIIUUUUUU!!!\n");
            map.displayGrid(monster);
        }

        else {
            map.displayGrid(false);
            System.out.println("My G, DEATH AWAITS YE!!!\n");
            map.displayGrid(monster);
        }

        System.out.println("\nM - monster"
                + "\nW - win"
        );
    }

    public static boolean landedOnMonster(Monster m, Map map) {
        int[] monsterCoordinates = m.getMonsterCoordinates();
        int[] playerCoordinates =  map.getPlayerCoordinates();
        if (monsterCoordinates[0] == playerCoordinates[0] && monsterCoordinates[1] == playerCoordinates[1]) {
            return true;
        }
        return false;
    }
}
