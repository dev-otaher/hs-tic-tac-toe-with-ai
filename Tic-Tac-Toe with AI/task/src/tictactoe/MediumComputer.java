package tictactoe;

public class MediumComputer extends EasyComputer {
    public MediumComputer(char letter, Board board) {
        super(letter, board);
    }

    private boolean isOneLetterMissing(String token, char letter) {
        int letterCount = Helper.countChar(token, letter);
        int emptyCount = Helper.countChar(token, '_');
        return letterCount == 2 && emptyCount == 1;
    }

    private int[] traverseRows(char letter) {
        for (int i = 0; i < board.getState().length; i++) {
            String row = String.valueOf(board.getState()[i]);
            if (isOneLetterMissing(row, letter)) {
                int emptyIndex = row.indexOf('_');
                return new int[]{i + 1, emptyIndex + 1};
            }
        }
        return null;
    }

    private int[] traverseColumns(char letter) {
        String column = "";
        for (int i = 0; i < board.getState().length; i++) {
            for (int j = 0; j < board.getState()[i].length; j++) {
                column += board.getState()[j][i];
            }
            if (isOneLetterMissing(column, letter)) {
                int emptyIndex = column.indexOf('_');
                return new int[]{emptyIndex + 1, i + 1};
            }
            column = "";
        }
        return null;
    }

    private int[] traverseDiagonal(char letter) {
        String diagonal = "";
        for (int i = 0; i < board.getState().length; i++) {
            for (int j = 0; j < board.getState()[i].length; j++) {
                if (i == j) {
                    diagonal += board.getState()[i][j];
                }
            }
        }
        if (isOneLetterMissing(diagonal, letter)) {
            int emptyIndex = diagonal.indexOf('_');
            return new int[]{emptyIndex + 1, emptyIndex + 1};
        }
        return null;
    }

    private int[] traverseDiagonal2(char letter) {
        String diagonal2 = "";
        for (int i = 0; i < board.getState().length; i++) {
            for (int j = 0; j < board.getState()[i].length; j++) {
                if (i + j == board.getState().length - 1) {
                    diagonal2 += board.getState()[i][j];
                }
            }
        }
        if (isOneLetterMissing(diagonal2, letter)) {
            int emptyIndex = diagonal2.indexOf('_');
            return new int[]{emptyIndex + 1, board.getState().length - emptyIndex};
        }
        return null;
    }

    private int[] winByOneMove(char letter) {
        int[] move = traverseRows(letter);
        if (move != null) {
            return move;
        }
        move = traverseColumns(letter);
        if (move != null) {
            return move;
        }
        move = traverseDiagonal(letter);
        if (move != null) {
            return move;
        }
        return traverseDiagonal2(letter);
    }

    @Override
    public String getLevel() {
        return "medium";
    }

    @Override
    public int[] getMove() {
        if (!board.isEmpty()) {
            int[] move = winByOneMove(letter);
            if (move != null) {
                return move;
            }
            char opponentLetter = letter == 'X' ? 'O' : 'X';
            move = winByOneMove(opponentLetter);
            if (move != null) {
                return move;
            }
        }
        return super.getMove();
    }

}