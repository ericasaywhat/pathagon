import java.util.ArrayList;

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

    public int isPathMade() {
        boolean didWhiteWin = false;
        boolean didBlackWin = false;

        for (int i = 0; i < 7; i++) {
            didWhiteWin = checkTileForPath(board[0][i], new ArrayList<Tile>(), 6, -6, 0);
            didBlackWin = checkTileForPath(board[i][0], new ArrayList<Tile>(), -6, 6, 1);
            return (didWhiteWin) ? 0 : 1;
        }
        return -1;
    }

    public boolean checkTileForPath(Tile tile, ArrayList<Tile> visited, int goalX, int goalY, int color) {
        if (tile == null) {
            return false;
        } else {
            if (tile.getX() == goalX || tile.getY() == goalY) {
                return true;
            }

            visited.add(tile);

            if (tile.getLeftNeighbor() != null
                    && !visited.contains(tile.getLeftNeighbor())
                    && tile.getLeftNeighbor().getColor() == color) {
                checkTileForPath(tile.getLeftNeighbor(), visited, goalX, goalY, color);
            }

            if (tile.getRightNeighbor() != null
                    && !visited.contains(tile.getRightNeighbor())
                    && tile.getRightNeighbor().getColor() == color) {
                checkTileForPath(tile.getRightNeighbor(), visited, goalX, goalY, color);
            }

            if (tile.getTopNeighbor() != null
                    && !visited.contains(tile.getTopNeighbor())
                    && tile.getTopNeighbor().getColor() == color) {
                checkTileForPath(tile.getTopNeighbor(), visited, goalX, goalY, color);
            }

            if (tile.getBottomNeighbor() != null
                    && !visited.contains(tile.getBottomNeighbor())
                    && tile.getBottomNeighbor().getColor() == color) {
                checkTileForPath(tile.getBottomNeighbor(), visited, goalX, goalY, color);
            }

            return false;
        }
    }

    public String toString() {
        String s = "";
        for (int i = 6; i >= 0; i--) { // print top-down
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == null) {
                    s += "_ ";
                } else {
                    s += board[i][j].getColor() + " ";
                }

                if (j == 6) {
                    s += "\n";
                }
            }
        }
        return s;
    }

    public static void main (String[] args) {
        Board b = new Board();
        Tile test = new Tile(0);
        test.setCoordinates(0, 0);
        b.placeTile(test);
        System.out.println(b.toString());
    }
}