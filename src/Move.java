public class Move {

    char player;

    int row;
    int col;

    public Move(char player, int row, int col) {
        this.player = player;
        this.row = row;
        this.col = col;
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "Move{" +
                "player=" + player +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}
