package org.example;

import java.util.Scanner;

public class NumberMonster extends Monster {
    private int randNumber = Utils.generateRandomNumber(1, 3);

    public int monsterQuestion() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Guess my number between 1 and 3");
        int playerNum= sc.nextInt();

        if (playerNum == randNumber) {
            return 1;
        } else {
            return -1;
        }
    }
}
