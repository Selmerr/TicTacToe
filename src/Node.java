import java.util.ArrayList;
import java.util.List;

public class Node {

    char[][] board;
    Node parent;

    List<Node> childNodes;

    int value;

    final String player;


    public Node generateChildNode(int row, int col, char symbol) {

        char[][] newBoard = this.board;


        if(this.board[row][col] == ' ') {
            newBoard[row][col] = symbol;
            Node child = new Node(newBoard, this, new ArrayList<>(), 0, switchPlayer(this.player));
            addChildNode(this);
        }
        return null;
    }
    private String switchPlayer(String currentPlayer) {
        return currentPlayer.equals("max") ? "min" : "max";
    }
    public Node(char[][] board, Node parent, List<Node> childNodes, int value, String player) {
        this.board = board;
        this.parent = parent;
        this.childNodes = new ArrayList<>();
        this.value = value;
        this.player = player;
    }

    public Node(char[][] board, List<Node> childNodes, int value, String player) {
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
