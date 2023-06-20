package javaproject;
import java.util.Scanner;
public class TicTacToe {
        public void play() {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[3][3];
        int turn = 1;
        char player = 'X';
        boolean gameEnded = false;

        initializeBoard(board);

        while (!gameEnded) {
            displayBoard(board);

            int row, col;
            do {
                System.out.println("Player " + player + ", enter row (1-3) and column (1-3) separated by space:");
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
            } while (!isValidMove(board, row, col));

            board[row][col] = player;

            if (hasWon(board, player)) {
                displayBoard(board);
                System.out.println("Player " + player + " has won!");
                gameEnded = true;
            } else if (isDraw(board)) {
                displayBoard(board);
                System.out.println("It's a draw!");
                gameEnded = true;
            } else {
                turn++;
                player = turn % 2 == 0 ? 'O' : 'X';
            }
        }
        scanner.close();
    }

    private static void initializeBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = '-';
            }
        }
    }

    private static void displayBoard(char[][] board) {
        System.out.println("  1 2 3");
        for (int row = 0; row < board.length; row++) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isValidMove(char[][] board, int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Invalid row or column. Please enter values between 1-3.");
            return false;
        } else if (board[row][col] != '-') {
            System.out.println("That cell is already occupied. Please choose a different one.");
            return false;
        } else {
            return true;
        }
    }

    private static boolean hasWon(char[][] board, char player) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    private static boolean isDraw(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
