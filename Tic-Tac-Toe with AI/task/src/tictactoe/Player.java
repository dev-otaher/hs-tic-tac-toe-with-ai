package tictactoe;

import java.util.Random;

public class Player {
    private final char letter;
    private final boolean computer;

    public Player(char letter, String type) {
        this.letter = letter;
        computer = !type.equalsIgnoreCase("user");
    }

    public char getLetter() {
        return letter;
    }

    public boolean isComputer() {
        return computer;
    }

    public int[] getMove() {
        int lower = 1, upper = 3;
        Random random = new Random();
        int x = random.nextInt(upper - lower + 1) + lower;
        int y = random.nextInt(upper - lower + 1) + lower;
        return new int[]{x, y};
    }
}
