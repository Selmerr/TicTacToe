import java.util.List;
import java.util.Scanner;

public class TicTacToe {

    int value;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        int value;

        GameState state = new GameState(board, 'X');

        int[][] fieldValues = {{3,2,3},
                                {2,4,2},
                                {3,2,3}};

        int inf = 50;

        while(!state.checkWin() && !state.checkDraw()) {
            System.out.println("Current board");
            printBoard(state.getBoard());

            if(state.getPlayerToMove() == 'X') {
                System.out.println("Enter your move (row and column): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                if (row >= 0 && row < 3 && col >= 0 && col < 3 && state.getBoard()[row][col] == ' ') {
                    Move move = new Move('O', row, col);
                    state = state.makeMove(move);
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }
            else {
                // AI's turn
                System.out.println("AI is making a move...");
                Move bestMove = game.findBestMove(state);
                if (bestMove != null) {
                    state = state.makeMove(bestMove);
                    System.out.println("AI placed at " + bestMove.row + " " + bestMove.col);
                } else {
                    System.out.println("No valid moves for AI. This should not happen if game logic is correct.");
                }
            }

        }
        printBoard(state.getBoard());

    }
    // Utility method to print the board
    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int minimax(GameState state, int depth, boolean isMaximizing) {
        if (state.checkWin()) {
            return isMaximizing ? -10 + depth : 10 - depth;
        }
        if(state.checkDraw()) {
            return 0;
        }
        if(isMaximizing) {
            int bestValue = -50;
            List<Move> moves = state.generateMoves();
            for (Move move : moves) {
                GameState newState = state.makeMove(move);
                value = minimax(newState, depth +1,false);
                if (value > bestValue) {
                    bestValue = value;
                }

            }
            return bestValue;

        }
        //Minimizing
        else {
            int bestValue = 50;
            List<Move> moves = state.generateMoves();
            for (Move move : moves) {
                GameState newState = state.makeMove(move);
                value = minimax(newState, depth +1,true);
                if(value<bestValue) {
                    bestValue = value;
                }
            }
            return bestValue;
        }
    }

    private Move findBestMove(GameState state) {
        int bestValue = Integer.MIN_VALUE;
        Move bestMove = null;
        List<Move> moves = state.generateMoves();

        for (Move move : moves) {// Assuming GameState copy constructor correctly copies the board and toggles the player
            GameState newState = state.makeMove(move); // Apply move
            int moveValue = minimax(newState, 0, false); // Assuming 'false' means it's the minimizing player's turn next, adjust as needed

            if (moveValue > bestValue) {
                bestValue = moveValue;
                bestMove = move;
            }
        }

        return bestMove; // This is the best move found by Minimax
    }
}
