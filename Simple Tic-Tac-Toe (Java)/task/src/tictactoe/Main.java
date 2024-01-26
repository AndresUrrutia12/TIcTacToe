package tictactoe;

import java.util.Scanner;

public class Main {
    /**
     * The main method of the Tic Tac Toe game.
     * It initializes the game board and handles the game loop, where it takes user input for the cell to mark,
     * checks if the cell is valid and if the game has ended after each turn.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Initialize the game board
        int rows = 3;
        int columns = 3;
        char[][] grid = new char[rows][columns];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = ' ';
            }
        }
        System.out.println("---------");

        // Print the initial game board
        for (char row[] : grid) {
            System.out.print("| ");
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

        int userRow;
        int userCol;
        int turn = 0;
        // Game loop
        do {
            // Check if the input is a number
            if (!input.hasNextInt()) {
                System.out.println("You should enter numbers!");
                input.nextLine();
            } else {
                userRow = input.nextInt();
                userCol = input.nextInt();
                // Check if the coordinates are within the game board
                if (userRow > 3 || userCol > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (grid[userRow - 1][userCol - 1] != ' ') {
                    // Check if the cell is already occupied
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    // Mark the cell with the current player's symbol
                    if (turn % 2 == 0) {
                        grid[userRow - 1][userCol - 1] = 'X';
                    } else {
                        grid[userRow - 1][userCol - 1] = 'O';
                    }
                    turn++;
                }

            }
            // Print the game board after each turn
            System.out.println("---------");
            for (char row[] : grid) {
                System.out.print("| ");
                for (char cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println("|");
            }
            System.out.println("---------");

        } while (turn <= 8 && winner(grid).equals("Draw"));
        // Print the result of the game
        System.out.println(winner(grid));
    }

    /**
     * This method checks if there is a winner in the game.
     * It checks all possible winning combinations for both players.
     * @param cells the game board
     * @return the result of the game: "X wins", "O wins" or "Draw"
     */
    public static String winner(char[][] cells){
        boolean xWins = false;
        boolean oWins = false;
        char[] singleArray = new char[cells.length * cells[0].length];
        int index = 0;

        // Convert the 2D array to a 1D array
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                singleArray[index++] = cells[i][j];
            }
        }
        // All possible winning combinations
        int[][] winningCombinations = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };

        // Check all winning combinations
        for (int[] combination : winningCombinations) {
            if (singleArray[combination[0]] == 'X' && singleArray[combination[1]] == 'X' && singleArray[combination[2]] == 'X') {
                xWins = true;
            } else if (singleArray[combination[0]] == 'O' && singleArray[combination[1]] == 'O' && singleArray[combination[2]] == 'O') {
                oWins = true;
            }
        }

        // Return the result of the game
        if (xWins){
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else {
            return "Draw";
        }
    }

}
