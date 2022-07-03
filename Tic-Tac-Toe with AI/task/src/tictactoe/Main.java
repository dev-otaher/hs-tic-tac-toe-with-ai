package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void printState(char[][] state) {
        System.out.println("---------");
        System.out.println(Arrays.deepToString(state)
                .replace("], [", " | \n| ")
                .replace("[[", "| ")
                .replace("]]", " |")
                .replace(",", ""));
        System.out.println("---------");
    }

    public static void main(String[] args) {
        // write your code here
        Board board = new Board("_________");
        Judge judge = new Judge(board);
        Player computer = new Player('O');
        Player human = new Player('X');
        Scanner scanner = new Scanner(System.in);
        int x = -1, y = -1;
        boolean isHumanTurn = true;
        printState(board.getState());
        do {
            Player currentPlayer;
            if (isHumanTurn) {
                currentPlayer = human;
                System.out.print("Enter the coordinates: ");
                if (scanner.hasNextInt()) {
                    x = Integer.parseInt(scanner.next());
                }
                if (scanner.hasNextInt()) {
                    y = Integer.parseInt(scanner.next());
                }
            } else {
                currentPlayer = computer;
                int[] moves = computer.getMove();
                x = moves[0];
                y = moves[1];
            }

            if (x == -1 || y == -1) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            } else if (judge.isCoordinatesInRange(x, y)) {
                if (judge.isValidMove(x, y)) {
                    board.updateState(currentPlayer, x, y);
                    if (!isHumanTurn) {
                        System.out.println("Making move level \"easy\"");
                    }
                    printState(board.getState());
                    String assessment = judge.assessBoard(human, computer, currentPlayer);
                    if (assessment.equals("D") || assessment.equals("F")) {
                        break;
                    }
                    isHumanTurn = !isHumanTurn;
                } else {
                    if (isHumanTurn) {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        } while (true);
    }
}
