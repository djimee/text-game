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

    public static int playerNumMoves, monsterNumMoves;

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

        double treasureLastDist, playerLastDist;

        while (player.isActive() && !player.hasWon()) {
            map.displayGridEnd();

            boolean playerHasMoved = false;
            
            while (!playerHasMoved) {
                System.out.println("Player please enter the direction 'l r u d': ");
                String direction = (s.nextLine()).toLowerCase();
                if (c.checkValidMovement(player, direction)) {
                    c.moveEntity(player, map, direction);
                    playerNumMoves++;
                    treasureLastDist = c.getDistanceToEntity(player, treasure);
                    System.out.println("Treasure is " + treasureLastDist + "m away from you.\n");
                    if (c.landedOn(player, monster) == -1) {
                        player.setActive(false);
                    } else if (c.landedOn(player, treasure) == 1) {
                        player.setHasWon(true);
                        System.out.println("My G, You have found the treasure in " + playerNumMoves +
                                " move(s)... SIIUUUUUU!!!\n");
                    }
                    playerHasMoved = true;
                }
            }

            map.displayGridEnd();

            boolean monsterHasMoved = false;
            
            while (!monsterHasMoved) {
                System.out.println("Monster please enter the direction 'l r u d': ");
                String direction = (s.nextLine()).toLowerCase();
                if (c.checkValidMovement(monster, direction)) {
                    c.moveEntity(monster, map, direction);
                    playerLastDist = c.getDistanceToEntity(player, monster);
                    System.out.println("Player is " + playerLastDist + "m away from you.\n");
                    if (c.landedOn(player, monster) == -1) {
                        monsterHasMoved = true;
                        player.setActive(false);
                    }
                    monsterHasMoved = true;
                }
            }
        }

        if (player.hasWon())
           System.out.println("My G, You have found the treasure in " + playerNumMoves + " move(s)... SIIUUUUUU!!!\n");
        else
           System.out.println("My G, DEATH AWAITS YE!!!\n");

        map.displayGridEnd();
    }
}
