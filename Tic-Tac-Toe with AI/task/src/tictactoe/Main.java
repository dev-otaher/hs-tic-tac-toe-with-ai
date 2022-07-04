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
        Scanner scanner = new Scanner(System.in);
        String[] inputs;
        do {
            System.out.print("Input command: ");
            inputs = scanner.nextLine().split(" ");
            if (!inputs[0].equalsIgnoreCase("start") || inputs.length >= 3) {
                if (inputs[0].equals("exit")) {
                    continue;
                }
            } else {
                System.out.println("Bad parameters!");
                continue;
            }
            Player p1 = new Player('X', inputs[1]), p2 = new Player('O', inputs[2]);
            Board board = new Board("_________");
            Judge judge = new Judge(board);
            int x = -1, y = -1;
            Player currentPlayer = p1;
            printState(board.getState());
            while (true) {
                if (currentPlayer.isComputer()) {
                    int[] moves = currentPlayer.getMove();
                    x = moves[0];
                    y = moves[1];
                } else {
                    System.out.print("Enter the coordinates: ");
                    if (scanner.hasNextInt()) {
                        x = Integer.parseInt(scanner.next());
                    }
                    if (scanner.hasNextInt()) {
                        y = Integer.parseInt(scanner.nextLine().replace(" ", ""));
                    }
                }
                if (!judge.isValidInput(x, y)) {
                    System.out.println("You should enter numbers!");
                } else if (judge.isCoordinatesInRange(x, y)) {
                    if (judge.isValidMove(x, y)) {
                        board.updateState(currentPlayer, x, y);
                        if (currentPlayer.isComputer()) {
                            System.out.println("Making move level \"easy\"");
                        }
                        printState(board.getState());
                        String assessment = judge.assessBoard(p1, p2, currentPlayer);
                        if (assessment.equals("D") || assessment.equals("W")) {
                            break;
                        }
                        currentPlayer = currentPlayer == p1 ? p2 : p1;
                    } else {
                        if (!currentPlayer.isComputer()) {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            }
        } while (!inputs[0].equals("exit"));
    }
}
