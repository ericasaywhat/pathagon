public class Board {
    private Tile[][] board;

    public Board() {
        board = new Tile[7][7];
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void placeTile(Tile tile) {
        board[tile.getX()][tile.getY()] = tile;
        // TODO update neighbors of current tile and surrounding tiles
    }

    public void removeTile(Tile tile) {
        //TODO wipe tile from neighbors
    }

    public boolean isPathMade() {
        // TODO check for path from (0,0) to (0,7) or (0,0) to (7,0)
        // For any tile on either edge, see if neighbors reach other end
        return false;
    }
}