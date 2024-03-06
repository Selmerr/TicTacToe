import java.util.ArrayList;
import java.util.List;

public class Node {

    char[][] board;
    Node parent;

    List<Node> childNodes;

    int value;

    final String player;


    public Node generateChildNode(int row, int col, char symbol) {
        if (this.board[row][col] == ' ') { // Check if the move is legal
            char[][] newBoard = deepCopyBoard(this.board);
            newBoard[row][col] = symbol; // Make the move
            Node child = new Node(newBoard, this, 0, switchPlayer(this.player));
            this.addChildNode(child); // Add the new child node
            return child;
        }
        return null; // Return null if the move is not legal
    }

    // Helper method to deep copy the board
    private char[][] deepCopyBoard(char[][] original) {
        char[][] copy = new char[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }
    private String switchPlayer(String currentPlayer) {
        return currentPlayer.equals("max") ? "min" : "max";
    }
    public Node(char[][] board, Node parent, int value, String player) {
        this.board = board;
        this.parent = parent;
        this.childNodes = new ArrayList<>();
        this.value = value;
        this.player = player;
    }

    public Node(char[][] board, int value, String player) {
        this.board = board;
        this.childNodes = new ArrayList<>();
        this.value = value;
        this.player = player;
    }

    public void addChildNode(Node child) {
        if(childNodes==null) {
            childNodes = new ArrayList<>();
        }
        childNodes.add(child);
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getPlayer() {
        return player;
    }
}
