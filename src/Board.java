public class Board {
    private int[][] board;

    public Board() {
        board = new int[7][7];
    }

    public int[][] getBoard() {
        return board;
    }

    public void placeTile(int x, int y, int tileColor) {
        board[x][y] = tileColor;
    }

    public void removeTile(int x, int y) {
        placeTile(x, y, 0);
    }
}