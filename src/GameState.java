import java.util.ArrayList;
import java.util.List;

public class GameState {

    char[][] board;

    char playerToMove;

    Move bestMove;

    public GameState(char[][] board, char playerToMove) {
        this.board = board;
        this.playerToMove = playerToMove;
    }

    public GameState(char[][] board, char playerToMove, Move bestMove) {
        this.board = board;
        this.playerToMove = playerToMove;
        this.bestMove = bestMove;
    }

    public GameState(GameState currentState) {
        this.board = new char[currentState.board.length][];
        for (int i = 0; i < board.length; i++) {
            this.board[i] = new char[currentState.board[i].length];
            for (int j = 0; j < board[i].length; j++) { // Corrected to use board[i].length
                this.board[i][j] = currentState.board[i][j];
            }
        }
        this.playerToMove = currentState.playerToMove;
    }


    public List<Move> generateMoves() {
        List<Move> moves = new ArrayList<>();
        for (int i = 0; i<board.length; i++) {
            for (int j= 0; j<board.length; j++) {
                if (board[i][j] == ' ') {
                    Move move = new Move(playerToMove,i,j);
                    moves.add(move);
                }
            }
        }
        return moves;
    }

    public GameState makeMove(Move move) {
        GameState newState = new GameState(this);
        newState.board[move.row][move.col] = move.player;
        if (move.player == 'X') {
            newState.playerToMove = 'O';
        }
        if (move.player == 'O') {
            newState.playerToMove = 'X';
        }
        return newState;

    }

    public boolean checkFullBoard() {
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin() {
        //Check for rows
        if(board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0]!=' ')
            return true;
        if(board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0]!=' ')
            return true;
        if(board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0]!=' ')
            return true;
        //Check columns
        if(board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0]!=' ')
            return true;
        if(board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1]!=' ')
            return true;
        if(board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2]!=' ')
            return true;
        //Check diagonals
        if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0]!=' ') {
            return true;
        }
        if(board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2]!=' ') {
            return true;
        }
        return false;
    }

    public boolean checkDraw() {
        if(checkFullBoard() && !checkWin()) {
            return true;
        }
        return false;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public char getPlayerToMove() {
        return playerToMove;
    }

    public void setPlayerToMove(char playerToMove) {
        this.playerToMove = playerToMove;
    }

    public Move getBestMove() {
        return bestMove;
    }

    public void setBestMove(Move bestMove) {
        this.bestMove = bestMove;
    }
}
