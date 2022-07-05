package tictactoe;

import java.util.Arrays;

public class Board {
    private final char[][] state;
    private boolean empty;

    public Board(String stateString) {
        this.state = parseState(stateString);
        this.empty = Arrays.deepToString(state).equals("_________");
    }

    public char[][] getState() {
        return state;
    }

    public boolean isEmpty() {
        return empty;
    }

    private char[][] parseState(String stateString) {
        char[][] array = new char[3][3];
        int charIndex = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = stateString.charAt(charIndex);
                charIndex++;
            }
        }
        return array;
    }

    public void updateState(Player player, int x, int y) {
        this.empty = false;
        this.state[x - 1][y - 1] = player.getLetter();
    }
}
