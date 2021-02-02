package com.codecool.gladiator.view;

import java.util.Scanner;

/**
 * Basic console view implementation
 */
public class ConsoleView implements Viewable {

    @Override
    public void display(String text) {
        System.out.println(text);
    }

    @Override
    public int getNumberBetween(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int userNumber;

        do {
            do {
                try {
                    System.out.printf("Enter the number between %s and %s: ", min, max);
                    userNumber = Integer.parseInt(scanner.nextLine());
                    break;  // valid numberic value
                } catch (NumberFormatException e) {
                    System.out.println("It's not a number!");
                }
            } while (true);

            if (userNumber >= min && userNumber <= max)  // is in range
                break;

            System.out.println("The number is out of range!");
        } while (true);

        return userNumber;
    }
}
