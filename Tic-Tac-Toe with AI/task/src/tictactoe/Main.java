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

    public static Player generatePlayerByType(String type, char letter, Board board) {
        switch (type) {
            case "easy":
                return new EasyComputer(letter, board);
            case "medium":
                return new MediumComputer(letter, board);
            default:
                return new Player(letter);
        }
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
            Board board = new Board("_________");
            Judge judge = new Judge(board);
            Player p1 = generatePlayerByType(inputs[1], 'X', board);
            Player p2 = generatePlayerByType(inputs[2], 'O', board);
            Player currentPlayer = p1;
            printState(board.getState());
            while (true) {
                int[] move = currentPlayer.getMove();
                int x = move[0], y = move[1];
                if (!judge.isValidInput(x, y)) {
                    System.out.println("You should enter numbers!");
                } else if (judge.isCoordinatesInRange(x, y)) {
                    if (judge.isValidMove(x, y)) {
                        board.updateState(currentPlayer, x, y);
                        if (currentPlayer instanceof EasyComputer) {
                            System.out.printf("Making move level \"%s\"\n", currentPlayer.getLevel());
                        }
                        printState(board.getState());
                        String assessment = judge.assessBoard(p1, p2, currentPlayer);
                        if (assessment.equals("D") || assessment.equals("W")) {
                            break;
                        }
                        currentPlayer = currentPlayer == p1 ? p2 : p1;
                    } else {
                        if (!(currentPlayer instanceof EasyComputer)) {
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
