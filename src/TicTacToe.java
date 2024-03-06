import java.util.List;

public class TicTacToe {

    public static void main(String[] args) {
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        // Example move (AI plays 'X')
        bestMove(board, true); // Assuming true for 'X' as the maximizing player

        // Print board after AI's move
        printBoard(board);
    }

    // Implement bestMove to use Minimax to find and execute the best move
    public static void bestMove(char[][] board, boolean isMaximizing) {
        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int[] move = new int[]{-1, -1};

        for (int[] space : Minimax.getAvailableSpaces(board)) {
            board[space[0]][space[1]] = isMaximizing ? 'X' : 'O'; // Try move
            int score = Minimax.minimax(board, !isMaximizing); // Evaluate move
            board[space[0]][space[1]] = ' '; // Undo move

            if ((isMaximizing && score > bestScore) || (!isMaximizing && score < bestScore)) {
                bestScore = score;
                move = space;
            }
        }

        if (move[0] != -1 && move[1] != -1) {
            board[move[0]][move[1]] = isMaximizing ? 'X' : 'O'; // Execute the best move
        }
    }

    // Utility to print the board
    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }


}
