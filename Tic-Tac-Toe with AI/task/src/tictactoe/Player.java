package tictactoe;

import java.util.Random;

public class Player {
    private char letter;

    public Player(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    public int[] getMove() {
        int lower = 1, upper = 3;
        Random random = new Random();
        int x = random.nextInt(upper - lower + 1) + lower;
        int y = random.nextInt(upper - lower + 1) + lower;
        return new int[]{x, y};
    }
}
