package tictactoe;

import java.util.Scanner;

public class Player {
    protected final char letter;

    public Player(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    protected String getLevel() {
        return "";
    }

    public int[] getMove() {
        System.out.print("Enter the coordinates: ");
        Scanner scanner = new Scanner(System.in);
        int x = Integer.parseInt(scanner.next());
        int y = Integer.parseInt(scanner.next());
        return new int[]{x, y};
    }
}
