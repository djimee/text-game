package org.example;

public class Controller {

    private int treauresAdded = 0;
    private int monstersAdded = 0;
    private int playersAdded = 0;

    private Player[] players = new Player[App.numPlayers];
    private Monster[] monsters = new Monster[App.numMonsters];
    private Treasure[] treasures = new Treasure[App.numTreasures];

    private static Controller c = null;
    private Controller() {};

    public static Controller getController(){
        if (c == null)
            c = new Controller();
        return c;
    }

    public void addEntityToMap(Entity e, Map m) {
        int num = 0;
        int added = -1;
        int legend = 0;

        int[][] mapGrid = m.getGrid();

        if (e instanceof Monster) {
            e = (Monster)e;
            num = App.numMonsters;
            legend = -1;
            added = monstersAdded;
        }
        else if (e instanceof Treasure) {
            e = (Treasure)e;
            num = App.numTreasures;
            legend = 2;
            added = treauresAdded;
        }
        else if (e instanceof Player) {
            e = (Player)e;
            num = App.numPlayers;
            legend = 1;
            added = playersAdded;
        }
        
        while (added < num) {        
            int[] randomCoordinates = Utils.getRandomCoordinates();
            int x = randomCoordinates[1];
            int y = randomCoordinates[0];
            if(mapGrid[y][x] == 0) {
                mapGrid[y][x] = legend;

                if (e instanceof Monster) {
                    monsters[added] = new Monster();
                    monsters[added].setCoordinates(randomCoordinates);
                    monstersAdded = added+1;
                }
                else if (e instanceof Treasure) {
                    treasures[added] = new Treasure();
                    treasures[added].setCoordinates(randomCoordinates);
                    treauresAdded = added+1;
                }
                else if (e instanceof Player) {
                    players[added] = new Player(added);
                    players[added].setCoordinates(randomCoordinates);
                    playersAdded = added+1;
                }
                added++;
            }
        }
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
                check = (playerCoordinates[1] + 1) < App.SIZE;
                if(!check)
                    System.out.println("You cannot move further right. You are at the edge.\n");
                break;
            case "u":
                check = (playerCoordinates[0] - 1) >= 0;
                if(!check)
                    System.out.println("You cannot move further up. You are at the edge.\n");
                break;
            case "d":
                check = (playerCoordinates[0] + 1) < App.SIZE;
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
