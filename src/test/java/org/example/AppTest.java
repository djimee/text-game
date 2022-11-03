package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void validMovement() {
        Map map = new Map(5);
        Controller c = Controller.getController();
        c.addEntityToMap(new Player(), map);

        Player player = c.getPlayer();

        player.setCoordinates(new int[] {3,3});
        assertTrue(c.checkValidMovement(player, "l"));
        assertTrue(c.checkValidMovement(player, "r"));
        assertTrue(c.checkValidMovement(player, "u"));
        assertTrue(c.checkValidMovement(player, "d"));
    }

    @Test
    void testValidEdgesMovement() {
        Map map = new Map(5);
        Controller c = Controller.getController();
        c.addEntityToMap(new Player(), map);

        Player player = c.getPlayer();

        for(int i=0; i<App.SIZE; i++) {
            player.setCoordinates(new int[] {0,i});
            assertFalse(c.checkValidMovement(player, "u"));
        }

        for(int i=0; i<App.SIZE; i++) {
            player.setCoordinates(new int[] {i,0});
            assertFalse(c.checkValidMovement(player,"l"));
        }

        for(int i=0; i<App.SIZE; i++) {
            player.setCoordinates(new int[] {App.SIZE-1,i});
            assertFalse(c.checkValidMovement(player,"d"));
        }

        for(int i=0; i<App.SIZE; i++) {
            player.setCoordinates(new int[] {i,App.SIZE-1});
            assertFalse(c.checkValidMovement(player,"r"));
        }
    }

    @Test
    public void playerMoved() {
        Map map = new Map(5);
        Controller c = Controller.getController();
        c.addEntityToMap(new Player(), map);

        Player player = c.getPlayer();

        player.setCoordinates(new int[] {3,3});

        int oldPlyerX = player.getCoordinates()[0];
        c.moveEntity(player, map, "l");

        assertEquals(oldPlyerX, player.getCoordinates()[0]);
    }

    @Test
    public void foundTreasure() {
        Map map = new Map(5);
        Controller c = Controller.getController();
        c.addEntityToMap(new Player(), map);
        c.addEntityToMap(new Treasure(), map);

        Treasure treasure = c.getTreasure();
        Player player = c.getPlayer();

        player.setCoordinates(new int[] {3,3});
        treasure.setCoordinates(new int[] {3,3});

        assertEquals(2,c.landedOn(player, treasure));

        treasure.setCoordinates(new int[] {3,4});

        assertEquals(0,c.landedOn(player, treasure));
    }

    @Test
    public void checkDistanceFunc() {
        Map map = new Map(5);
        Controller c = Controller.getController();
        c.addEntityToMap(new Player(), map);
        c.addEntityToMap(new Treasure(), map);

        Treasure treasure = c.getTreasure();
        Player player = c.getPlayer();

        player.setCoordinates(new int[] {1,3});
        treasure.setCoordinates(new int[] {0,4});

        int y = player.getCoordinates()[0] - treasure.getCoordinates()[0];
        int x = player.getCoordinates()[1] - treasure.getCoordinates()[1];

        assertEquals(Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2))), c.getDistanceToEntity(player, treasure) );
    }
}
