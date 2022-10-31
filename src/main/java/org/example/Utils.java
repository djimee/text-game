package org.example;

import java.util.Random;

public class Utils {
    /**
     * Gets random Coordinates
     * @returns an array of size 2 [y, x]
     */
    public static int[] getRandomCoordinates() {
        Random rand = new Random();
        // get random coordinates
        int x = rand.nextInt(App.SIZE);
        int y = rand.nextInt(App.SIZE);

        int[] temp = new int[]{x,y};

        return temp;
    }

    public static int generateRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
}
