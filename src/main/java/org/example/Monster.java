package org.example;

public class Monster {
    private String name = null;
    private int[] monsterCoordinates = new int[2];
    private boolean active = false;

    public Monster () {
        this.setActive(true);
        setMonsterCoordinates();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getMonsterCoordinates() {
        return monsterCoordinates;
    }

    public void setMonsterCoordinates() {
        this.monsterCoordinates = Utils.getRandomCoordinates();
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
