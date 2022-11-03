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
}
