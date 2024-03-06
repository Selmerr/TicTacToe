public class TicTacToe {

    Node node;

    public static void main(String[] args) {

        char[][] board = {{' ',' ',' '},
                        {' ',' ',' '},
                        {' ',' ',' '}};
        char[][] values = {{3,2,3}, {2,4,2}, {3,2,3}};
        Node root = new Node(board, null, 0,"max");

    }
}
