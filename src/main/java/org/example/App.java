package org.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static int SIZE = 5;
    public static int numMonsters = 1;
    public static int numPlayers = 1;
    public static int numTreasures = 1;
    public static int numOfMoves = 0;

    public static void main( String[] args ) {
        Scanner s = new Scanner(System.in);
        Controller c = Controller.getController();
        Map map = new Map(SIZE);

        c.addEntityToMap(new Player(), map);
        c.addEntityToMap(new Treasure(), map);
        c.addEntityToMap(new Monster(), map);

        Player player = c.getPlayer();
        Monster monster = c.getMonster();
        Treasure treasure = c.getTreasure();

        while (player.isActive() && !player.hasWon()) {
            map.displayGrid();

            boolean playerHasMoved = false;
            
            while (!playerHasMoved) {
                System.out.println("Player please enter the direction 'l r u d': ");
                String direction = (s.nextLine()).toLowerCase();
                if (c.checkValidMovement(player, direction)) {
                    c.moveEntity(player, map, direction);
                    System.out.println("Treasure is " + c.getDistanceToEntity(player, treasure) + "m away from you.\n");

                    if (c.landedOn(player, monster) == -1)
                        player.setActive(false);
                    else if (c.landedOn(player, treasure) == 1)
                        player.setHasWon(true);

                    playerHasMoved = true;
                    numOfMoves++;
                }
            }

            map.displayGrid();

            boolean monsterHasMoved = false;
            
            while (!monsterHasMoved) {
                System.out.println("Monster please enter the direction 'l r u d': ");
                String direction = (s.nextLine()).toLowerCase();
                if (c.checkValidMovement(monster, direction)) {
                    c.moveEntity(monster, map, direction);
                    System.out.println("Player is " + c.getDistanceToEntity(player, monster) + "m away from you.\n");

                    if (c.landedOn(player, monster) == -1)
                        player.setActive(false);
                        
                    monsterHasMoved = true;
                }
            }
        }

        if(player.hasWon())
           System.out.println("My G, You have found the treasure in " + numOfMoves + " move(s)... SIIUUUUUU!!!\n");
        else
           System.out.println("My G, DEATH AWAITS YE!!!\n");

        map.displayGridEnd();
    }
}



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
