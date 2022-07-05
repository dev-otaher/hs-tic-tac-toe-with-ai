package tictactoe;

import java.util.Random;

public class EasyComputer extends Player {
    protected Board board;

    public EasyComputer(char letter, Board board) {
        super(letter);
        this.board = board;
    }

    public String getLevel() {
        return "easy";
    }

    public int[] getMove() {
        int lower = 1, upper = 3;
        Random random = new Random();
        int x = random.nextInt(upper - lower + 1) + lower;
        int y = random.nextInt(upper - lower + 1) + lower;
        return new int[]{x, y};
    }
}
