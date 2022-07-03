package tictactoe;

public class Board {
    private char[][] state;

    public Board(String stateString) {
        this.state = parseState(stateString);
    }

    public char[][] getState() {
        return state;
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
        this.state[x - 1][y - 1] = player.getLetter();
    }
}
