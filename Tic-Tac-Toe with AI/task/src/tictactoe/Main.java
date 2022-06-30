package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static char[][] parseState(Scanner scanner) {
        System.out.print("Enter the cells: ");
        return stateStringToArray(scanner.nextLine());
    }

    public static char[][] stateStringToArray(String state) {
        char[][] array = new char[3][3];
        int charIndex = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = state.charAt(charIndex);
                charIndex++;
            }
        }
        return array;
    }

    public static void printState(char[][] state) {
        System.out.println("---------");
        System.out.println(Arrays.deepToString(state)
                .replace("], [", " | \n| ")
                .replace("[[", "| ")
                .replace("]]", " |")
                .replace(",", ""));
        System.out.println("---------");
    }

    public static boolean isCoordinatesInRange(int x, int y) {
        return x >= 1 && x <= 3 && y >= 1 && y <= 3;
    }

    public static boolean isValidMove(char[][] state, int x, int y) {
        return state[x - 1][y - 1] == '_';
    }

    public static int countChar(String str, char c) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                counter++;
            }
        }
        return counter;
    }

    public static int countPlayerMovement(char[][] state, char player) {
        return countChar(Arrays.deepToString(state), player);
    }

    public static boolean isEqualMovements(char[][] state) {
        return countPlayerMovement(state, 'X') == countPlayerMovement(state, 'O');
    }

    public static char getCurrentPlayer(char[][] state) {
        if (isEqualMovements(state)) {
            return 'X';
        } else {
            return 'O';
        }
    }

    public static void updateState(char[][] state, char player, int x, int y) {
        state[x - 1][y - 1] = player;
    }

    public static boolean isArrayIdentical(char[] array) {
        for (char ch : array) {
            if (ch != array[0]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isWinnerByRow(char[][] state, char playerLetter) {
        for (char[] row : state) {
            if (row[0] == playerLetter && isArrayIdentical(row)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWinnerByColumn(char[][] state, char playerLetter) {
        for (int c = 0; c < state.length; c++) {
            char[] array = new char[state.length];
            for (int r = 0; r < state[c].length; r++) {
                array[r] = state[r][c];
            }
            if (array[0] == playerLetter && isArrayIdentical(array)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWinnerByDiagonal(char[][] state, char playerLetter) {
        char[] array = new char[state.length];
        for (int r = 0; r < state.length; r++) {
            for (int c = 0; c < state[r].length; c++) {
                if (r == c) {
                    array[r] = state[r][c];
                }
            }
        }
        return array[0] == playerLetter && isArrayIdentical(array);
    }

    public static boolean isWinnerByOtherDiagonal(char[][] state, char playerLetter) {
        char[] array = new char[state.length];
        for (int r = 0; r < state.length; r++) {
            for (int c = 0; c < state[r].length; c++) {
                if (r + c == state.length - 1) {
                    array[r] = state[r][c];
                }
            }
        }
        return array[0] == playerLetter && isArrayIdentical(array);
    }

    public static boolean isWinner(char[][] state, char playerLetter) {
        boolean isWinner = isWinnerByRow(state, playerLetter);
        if (!isWinner) {
            isWinner = isWinnerByColumn(state, playerLetter);
        }
        if (!isWinner) {
            isWinner = isWinnerByDiagonal(state, playerLetter);
        }
        if (!isWinner) {
            isWinner = isWinnerByOtherDiagonal(state, playerLetter);
        }
        return isWinner;
    }

    public static void assessState(char[][] state, char currentPlayer) {
        int emptyCount = countPlayerMovement(state, '_');
        if (!isWinner(state, 'X') && !isWinner(state, 'O') && emptyCount == 0) {
            System.out.println("Draw");
        } else if (isWinner(state, currentPlayer)) {
            System.out.println(currentPlayer + " wins");
        } else {
            System.out.println("Game not finished");
        }
    }

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        char[][] state = parseState(scanner);
        printState(state);

        int x = -1, y = -1;
        do {
            System.out.print("Enter the coordinates: ");
            if (scanner.hasNextInt()) {
                x = Integer.parseInt(scanner.next());
            }
            if (scanner.hasNextInt()) {
                y = Integer.parseInt(scanner.next());
            }

            if (x == -1 || y == -1) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            } else if (isCoordinatesInRange(x, y)) {
                if (isValidMove(state, x, y)) {
                    char currentPlayer = getCurrentPlayer(state);
                    updateState(state, currentPlayer, x, y);
                    printState(state);
                    assessState(state, currentPlayer);
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        } while (true);

    }
}
