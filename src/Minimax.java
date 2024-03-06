import java.util.ArrayList;
import java.util.List;

public class Minimax {

    // Check if any player has won or if it's a draw
    public static boolean isGameOver(char[][] board) {
        return (winner(board, 'X') || winner(board, 'O') || getAvailableSpaces(board).isEmpty());
    }

    // Evaluate the board for a win/lose/draw condition
    public static int evaluate(char[][] board) {
        if (winner(board, 'X')) {
            return 10; // 'X' is the maximizing player
        } else if (winner(board, 'O')) {
            return -10; // 'O' is the minimizing player
        } else {
            return 0; // Draw
        }
    }

    // Check if a player has won
    public static boolean winner(char[][] board, char player) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) || // Rows
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) { // Columns
                return true;
            }
        }
        // Diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }

    // Get available spaces on the board for moves
    public static List<int[]> getAvailableSpaces(char[][] board) {
        List<int[]> availableSpaces = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    availableSpaces.add(new int[]{i, j});
                }
            }
        }
        return availableSpaces;
    }
    public static int minimax(char[][] board, boolean isMaximizingPlayer) {
        if (isGameOver(board)) {
            return evaluate(board);
        }

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (int[] space : getAvailableSpaces(board)) {
                // Try move for 'X'
                board[space[0]][space[1]] = 'X';
                int eval = minimax(board, false); // Switch to Minimizing player
                board[space[0]][space[1]] = ' '; // Undo move
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int[] space : getAvailableSpaces(board)) {
                // Try move for 'O'
                board[space[0]][space[1]] = 'O';
                int eval = minimax(board, true); // Switch to Maximizing player
                board[space[0]][space[1]] = ' '; // Undo move
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }
}
