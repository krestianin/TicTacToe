package ca.cmpt213;

public class GameLogic {
    private int[][] board;
    private boolean isXTurn;
    private static final int SIZE = 4;

    public GameLogic() {
        board = new int[SIZE][SIZE];
        isXTurn = true; // X starts the game
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col] == 0) {
            board[row][col] = isXTurn ? 1 : 2;
            isXTurn = !isXTurn;
            return true;
        }
        return false;
    }

    public boolean checkForWin() {
        for (int i = 0; i < SIZE; i++) {
            if ((checkLine(board[i][0], board[i][1], board[i][2], board[i][3])) ||
                (checkLine(board[0][i], board[1][i], board[2][i], board[3][i]))) {
                return true;
            }
        }
        
        return checkLine(board[0][0], board[1][1], board[2][2], board[3][3]) ||
               checkLine(board[0][3], board[1][2], board[2][1], board[3][0]);
    }

    private boolean checkLine(int a, int b, int c, int d) {
        return (a != 0) && (a == b) && (b == c) && (c == d);
    }

    public boolean isDraw() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetGame() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = 0;
            }
        }
        isXTurn = true;
    }

    public boolean isXTurn() {
        return isXTurn;
    }
}
