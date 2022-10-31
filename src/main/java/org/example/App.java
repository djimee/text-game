package org.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static int SIZE = 2;
    public static int numMonsters = 2;
    public static int numPlayers = 2;
    public static int numTreasures = 1;
    public static int numOfMoves = 0;

    public static void main( String[] args ) {
        Map map = new Map(SIZE);
        boolean playerAlive = true;
        boolean hasWon = false;
        Scanner s = new Scanner(System.in);

        Player[] players = new Player[numPlayers];
        Monster[] monster = new Monster[numMonsters];

        int[][] mapGrid = map.getGrid();

        map.displayGrid();

        // refactor
        int monstersAdded = 0;
        while (monstersAdded < numMonsters) {
            monster[monstersAdded] = new Monster();
            int[] randomCoordinates = Utils.getRandomCoordinates();
            int x = randomCoordinates[1];
            int y = randomCoordinates[0];
            if (mapGrid[y][x] == 0) {
                mapGrid[y][x] = -1;
                monstersAdded++;
            }
        }

        int playersAdded = 0;
        while (playersAdded < numPlayers) {
            monster[playersAdded] = new Monster();
            int[] randomCoordinates = Utils.getRandomCoordinates();
            int x = randomCoordinates[1];
            int y = randomCoordinates[0];
            if (mapGrid[y][x] == 0) {
                mapGrid[y][x] = 1;
                playersAdded++;
            }
        }

        int treasuresAdded = 0;
        while (treasuresAdded < numTreasures) {
            monster[treasuresAdded] = new Monster();
            int[] randomCoordinates = Utils.getRandomCoordinates();
            int x = randomCoordinates[1];
            int y = randomCoordinates[0];
            if (mapGrid[y][x] == 0) {
                mapGrid[y][x] = 2;
                treasuresAdded++;
            }
        }

        int currTurn = 1;

        while (playerAlive && !hasWon) {
//            map.displayGrid(false);
            System.out.println("Please enter the direction 'l r u d': ");
            String direction = (s.nextLine()).toLowerCase();
            s = new Scanner(System.in);

            if (player.checkValidMovement(direction)) {
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
            numOfMoves++;
        }

        if(hasWon) {
            map.displayGrid(true);
            System.out.println("My G, You have found the treasure in " + numOfMoves + " move(s)... SIIUUUUUU!!!\n");
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

    public static int landedOn(Player p, Entity e) {
        int[] entityCoordinates = e.getCoordinates();
        int[] playerCoordinates =  p.getCoordinates();
        if (entityCoordinates[0] == playerCoordinates[0] && entityCoordinates[1] == entityCoordinates[1]) {
            if (e instanceof Monster) {
                return -1;
            }
            else if (e instanceof Treasure) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Gets displacement to the treasure
     * @returns double type displacement
     */
    public double getDistanceToEntity(Player p, Entity t) {
        int y = p.getCoordinates()[0] - t.getCoordinates()[0];
        int x = p.getCoordinates()[1] - t.getCoordinates()[1];
        return Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
    }
}
