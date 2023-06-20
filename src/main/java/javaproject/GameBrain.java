package javaproject;

import java.util.Scanner;

public class GameBrain {
    void initialize() {
        System.out.println("Welcome to the 3 in 1 Java Game\nPick a game to play");
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Tic Tac Toe\n2. Number Guessing \n3. Quiz\n4. Exit.\n");
        while (true) {
            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        new TicTacToe().play();
                        break;
                    case 2:
                        new NumberGuessing().play();
                        break;
                    case 3:
                        new Quiz().play();
                        break;
                    case 4:
                        sc.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Enter a valid choice");
                        break;
                }
                // Add a prompt to choose another game
                System.out.println("Choose another game or enter 4 to exit.\n");
            } catch (NumberFormatException e) {
                sc.close();
                System.out.println("Please enter a number");
            }
        }
    }
}
