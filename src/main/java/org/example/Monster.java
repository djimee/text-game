package org.example;

public class Monster extends Entity {

    public void moveMonster(String direction) {
        int[] monsterCoordinates = getCoordinates();
        switch(direction) {
            case "l":
                monsterCoordinates[1] -= 1;
                break;
            case "r":
                monsterCoordinates[1] += 1;
                break;
            case "u":
                monsterCoordinates[0] -= 1;
                break;
            case "d":
                monsterCoordinates[0] += 1;
                break;
        }
    }
}
