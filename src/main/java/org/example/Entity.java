package org.example;

public abstract class Entity {

    private String name = null;
    private boolean active = true;
    private int type = 0;

    private int[] coordinates = new int[2];

    public int[] getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public void setRandomCoordinates() {
        this.coordinates = Utils.getRandomCoordinates();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public int getType() {
        return this.type;
    }
}
