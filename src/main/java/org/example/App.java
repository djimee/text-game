package org.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static int SIZE = 5;
    public static int numMonsters = 2;
    public static int numPlayers = 2;
    public static int numTreasures = 1;
    public static int numOfMoves = 0;

    public static void main( String[] args ) {
        Map map = new Map(SIZE);
        boolean playerAlive = true;
        boolean hasWon = false;
        Scanner s = new Scanner(System.in);

        Controller c = Controller.getController();

        c.addEntityToMap(new Player(0), map);
        c.addEntityToMap(new Treasure(), map);
        c.addEntityToMap(new Monster(), map);

//        map.displayGrid();

        map.displayGrid();

        int currTurn = 1;

//        while (playerAlive && !hasWon) {
////            map.displayGrid(false);
//            System.out.println("Please enter the direction 'l r u d': ");
//            String direction = (s.nextLine()).toLowerCase();
//            s = new Scanner(System.in);
//
//            if (player.checkValidMovement(direction)) {
//                map.movePlayer(direction);
//                System.out.println("Treasure is " + map.getDistanceToTreasure() + "m away from you.\n");
//                for(Monster m : monster) {
//                    if(landedOnMonster(m, map)) {
//                        playerAlive = false;
//                        break;
//                    }
//                }
//                hasWon = map.isTreasureFound();
//            }
//            numOfMoves++;
//        }
//
//        if(hasWon) {
//            map.displayGrid(true);
//            System.out.println("My G, You have found the treasure in " + numOfMoves + " move(s)... SIIUUUUUU!!!\n");
//            map.displayGrid(monster);
//        }
//
//        else {
//            map.displayGrid(false);
//            System.out.println("My G, DEATH AWAITS YE!!!\n");
//            map.displayGrid(monster);
//        }
//
//        System.out.println("\nM - monster"
//                + "\nW - win"
//        );
    }    
}
