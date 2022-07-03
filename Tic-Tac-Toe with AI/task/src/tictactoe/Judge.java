package tictactoe;

import java.util.Arrays;

public class Judge {
    private final Board board;

    public Judge(Board board) {
        this.board = board;
    }

    public boolean isWinnerByRow(Player player) {
        for (char[] row : board.getState()) {
            if (row[0] == player.getLetter() && Helper.isArrayIdentical(row)) {
                return true;
            }
        }
        return false;
    }

    public boolean isWinnerByColumn(Player player) {
        for (int c = 0; c < board.getState().length; c++) {
            char[] array = new char[board.getState().length];
            for (int r = 0; r < board.getState()[c].length; r++) {
                array[r] = board.getState()[r][c];
            }
            if (array[0] == player.getLetter() && Helper.isArrayIdentical(array)) {
                return true;
            }
        }
        return false;
    }

    public boolean isWinnerByDiagonal(Player player) {
        char[] array = new char[board.getState().length];
        for (int r = 0; r < board.getState().length; r++) {
            for (int c = 0; c < board.getState()[r].length; c++) {
                if (r == c) {
                    array[r] = board.getState()[r][c];
                }
            }
        }
        return array[0] == player.getLetter() && Helper.isArrayIdentical(array);
    }

    public boolean isWinnerByOtherDiagonal(Player player) {
        char[] array = new char[board.getState().length];
        for (int r = 0; r < board.getState().length; r++) {
            for (int c = 0; c < board.getState()[r].length; c++) {
                if (r + c == board.getState().length - 1) {
                    array[r] = board.getState()[r][c];
                }
            }
        }
        return array[0] == player.getLetter() && Helper.isArrayIdentical(array);
    }

    public boolean isWinner(Player player) {
        boolean isWinner = isWinnerByRow(player);
        if (!isWinner) {
            isWinner = isWinnerByColumn(player);
        }
        if (!isWinner) {
            isWinner = isWinnerByDiagonal(player);
        }
        if (!isWinner) {
            isWinner = isWinnerByOtherDiagonal(player);
        }
        return isWinner;
    }

    public boolean isCoordinatesInRange(int x, int y) {
        return x >= 1 && x <= 3 && y >= 1 && y <= 3;
    }

    public boolean isValidMove(int x, int y) {
        return board.getState()[x - 1][y - 1] == '_';
    }

    public int countPlayerMovements(char player) {
        return Helper.countChar(Arrays.deepToString(board.getState()), player);
    }

    public boolean isMovementsEqual() {
        return countPlayerMovements('X') == countPlayerMovements('O');
    }

    public char getCurrentPlayer() {
        if (isMovementsEqual()) {
            return 'X';
        } else {
            return 'O';
        }
    }

    public String assessBoard(Player p1, Player p2, Player currentPlayer) {
        int emptyCells = countPlayerMovements('_');
        if (!isWinner(p1) && !isWinner(p2) && emptyCells == 0) {
            System.out.println("Draw");
            return "D";
        } else if (isWinner(currentPlayer)) {
            System.out.println(currentPlayer.getLetter() + " wins");
            return "F";
        }
        return "NF";
    }
}
