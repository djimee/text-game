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
        Map map = new Map(SIZE);
        boolean playerAlive = true;
        boolean hasWon = false;
        Scanner s = new Scanner(System.in);

        Controller c = Controller.getController();

        c.addEntityToMap(new Player(), map);
        c.addEntityToMap(new Treasure(), map);
        c.addEntityToMap(new Monster(), map);

        Player player = c.getPlayer();
        Monster monster = c.getMonster();
        Treasure treasure = c.getTreasure();

//        map.displayGrid();

//<<<<<<< HEAD
//        // refactor
//        Player player = new Player();
//        int[] randomCoordinates = Utils.getRandomCoordinates();
//        int x = randomCoordinates[1];
//        int y = randomCoordinates[0];
//        if (mapGrid[y][x] == 0) {
//            mapGrid[y][x] = -1;
//            player.setCoordinates(new int[]{x, y});
//        }
//
//        Monster monster = new Monster();
//        randomCoordinates = Utils.getRandomCoordinates();
//        x = randomCoordinates[1];
//        y = randomCoordinates[0];
//        if (mapGrid[y][x] == 0) {
//            mapGrid[y][x] = 1;
//            monster.setCoordinates(new int[]{x, y});
//        }
//
//        Treasure treasure = new Treasure();
//        randomCoordinates = Utils.getRandomCoordinates();
//        x = randomCoordinates[1];
//        y = randomCoordinates[0];
//        if (mapGrid[y][x] == 0) {
//            mapGrid[y][x] = 1;
//            treasure.setCoordinates(new int[]{x, y});
//        }
//
////        int treasuresAdded = 0;
////        while (treasuresAdded < numTreasures) {
////            treasures[treasuresAdded] = new Treasure();
////            randomCoordinates = Utils.getRandomCoordinates();
////            x = randomCoordinates[1];
////            y = randomCoordinates[0];
////            if (mapGrid[y][x] == 0) {
////                mapGrid[y][x] = 2;
////                treasures[treasuresAdded].setCoordinates(new int[]{x, y});
////                treasuresAdded++;
////            }
////        }
//
//=======
//>>>>>>> MVP-3

        while (player.isActive() && !player.hasWon()) {
            map.displayGrid();
            System.out.println("Player please enter the direction 'l r u d': ");
            String direction = (s.nextLine()).toLowerCase();
            s = new Scanner(System.in);

            boolean playerHasMoved = false;

            while (!playerHasMoved) {
                if (c.checkValidMovement(player, direction)) {
                    c.moveEntity(player, map, direction);
                    System.out.println("Treasure is " + c.getDistanceToEntity(player, treasure) + "m away from you.\n");
                    if (c.landedOn(player, monster) == -1) {
                        player.setActive(false);
                        playerHasMoved = true;
                    } else if (c.landedOn(player, treasure) == 1) {
                        player.setHasWon(true);
                        System.out.println("My G, You have found the treasure in move(s)... SIIUUUUUU!!!\n");
                        playerHasMoved = true;
                    }
                    playerHasMoved = true;
                }
            }

            map.displayGrid();

            boolean monsterHasMoved = false;
            System.out.println("Monster please enter the direction 'l r u d': ");

            while (!monsterHasMoved) {
                if (c.checkValidMovement(monster, direction)) {
                    direction = (s.nextLine()).toLowerCase();
                    s = new Scanner(System.in);
                    c.moveEntity(player, map, direction);
                    System.out.println("Player is " + c.getDistanceToEntity(player, monster) + "m away from you.\n");
                    if (c.landedOn(player, monster) == -1) {
                        monsterHasMoved = true;
                        player.setActive(false);
                    }
                    monsterHasMoved = true;
                }
            }
        }
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
