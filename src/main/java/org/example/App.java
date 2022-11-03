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

    private static int playerNumMoves, monsterNumMoves ;

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

        System.out.print("Player, what do you wish to be called? ");
        player.setName(s.nextLine());

        System.out.print("Monster, what do you wish to be called? ");
        monster.setName(s.nextLine());

        System.out.println("\n===========================================================");

        double treasureLastDist, playerLastDist;

        while (player.isActive() && !player.hasWon()) {
            map.displayGrid();

            boolean playerHasMoved = false;
            treasureLastDist = c.getDistanceToEntity(player, treasure);
            System.out.println("The treasure is currently " + treasureLastDist + "m away from you!!");

            while (!playerHasMoved) {

                System.out.print(player.getName() + ", please enter the direction you wish to travel... 'l r u d': ");
                String direction = (s.nextLine()).toLowerCase();

                if (c.checkValidMovement(player, direction)) {
                    c.moveEntity(player, map, direction);
                    playerNumMoves++;

                    System.out.println("\n===========================================================");

                    if (c.landedOn(player, monster) == -1) {
                        player.setActive(false);
                    } else if (c.landedOn(player, treasure) == 2) {
                        player.setHasWon(true);
                    }

                    playerHasMoved = true;
                }
            }

            if (player.hasWon()) {
                System.out.println("My G, You have found the treasure in " +
                        playerNumMoves + " move(s)... SIIUUUUUU!!!\n");
                break;
            } else if (!player.isActive()) {
                System.out.println("MY G, DEATH AWAITS YE!!! YOU HAVE BEEN FOUND IN " + 
                        monsterNumMoves + " MOVE(S)!!!\n");
                break;
            }

            map.displayGrid();

            boolean monsterHasMoved = false;
            playerLastDist = c.getDistanceToEntity(player, monster);
            System.out.println(player.getName() + " is currently " + playerLastDist + "m away from you!!");

            while (!monsterHasMoved) {

                System.out.print(monster.getName() + ", please enter the direction you wish to travel... 'l r u d': ");
                String direction = (s.nextLine()).toLowerCase();

                if (c.checkValidMovement(monster, direction)) {
                    c.moveEntity(monster, map, direction);
                    monsterNumMoves++;

                    System.out.println("\n===========================================================");

                    if (c.landedOn(player, monster) == -1) {
                        player.setActive(false);
                    }

                    monsterHasMoved = true;
                }
            }

            if (!player.isActive()) {
                System.out.println("MY G, DEATH AWAITS YE!!! YOU HAVE BEEN FOUND IN " + monsterNumMoves + " MOVE(S)!!!\n");
                break;
            }
        }

        map.displayGridEnd();
    }
}
