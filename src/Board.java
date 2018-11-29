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
        if (tile.getX()+1 < 7 && board[tile.getX()+1][tile.getY()] != null) {
            board[tile.getX()+1][tile.getY()].setLeftNeighbor(tile);
            tile.setRightNeighbor(board[tile.getX()+1][tile.getY()]);
        }
        if (tile.getX()-1 > 0 && board[tile.getX()-1][tile.getY()] != null) {
            board[tile.getX()-1][tile.getY()].setRightNeighbor(tile);
            tile.setLeftNeighbor(board[tile.getX()-1][tile.getY()]);
        }
        if (tile.getY()+1 < 7 && board[tile.getX()][tile.getY()+1] != null) {
            board[tile.getX()][tile.getY()+1].setBottomNeighbor(tile);
            tile.setTopNeighbor(board[tile.getX()][tile.getY()+1]);
        }
        if (tile.getY()-1 > 0 && board[tile.getX()][tile.getY()-1] != null) {
            board[tile.getX()][tile.getY()-1].setTopNeighbor(tile);
            tile.setBottomNeighbor(board[tile.getX()][tile.getY()-1]);
        }
    }

    public void removeTile(Tile tile) {
        board[tile.getX()][tile.getY()] = null;
        if (tile.getX()+1 < 7 && board[tile.getX()+1][tile.getY()] != null) {
            board[tile.getX()+1][tile.getY()].setLeftNeighbor(null);
        }
        if (tile.getX()-1 > 0 && board[tile.getX()-1][tile.getY()] != null) {
            board[tile.getX()-1][tile.getY()].setRightNeighbor(null);
        }
        if (tile.getY()+1 < 7 && board[tile.getX()][tile.getY()+1] != null) {
            board[tile.getX()][tile.getY()+1].setBottomNeighbor(null);
        }
        if (tile.getY()-1 > 0 && board[tile.getX()][tile.getY()-1] != null) {
            board[tile.getX()][tile.getY()-1].setTopNeighbor(null);
        }
    }

    public int isPathMade() {
        boolean didWhiteWin = false;
        boolean didBlackWin = false;

        for (int i = 0; i < 7; i++) {
            didWhiteWin = checkTileForPath(board[0][i], new ArrayList<Tile>(), 6, -6, 0);
            didBlackWin = checkTileForPath(board[i][0], new ArrayList<Tile>(), -6, 6, 1);
            if (didWhiteWin == true) {
                return 0;
            } else if (didBlackWin == true) {
                return 1;
            }
        }
        return -1;
    }

    public boolean checkTileForPath(Tile tile, ArrayList<Tile> visited, int goalX, int goalY, int color) {
        boolean flag;

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
                flag = checkTileForPath(tile.getLeftNeighbor(), visited, goalX, goalY, color);
                if (flag) {
                    return true;
                }
            }

            if (tile.getRightNeighbor() != null
                    && !visited.contains(tile.getRightNeighbor())
                    && tile.getRightNeighbor().getColor() == color) {
                flag = checkTileForPath(tile.getRightNeighbor(), visited, goalX, goalY, color);
                if (flag) {
                    return true;
                }
            }

            if (tile.getTopNeighbor() != null
                    && !visited.contains(tile.getTopNeighbor())
                    && tile.getTopNeighbor().getColor() == color) {
                flag = checkTileForPath(tile.getTopNeighbor(), visited, goalX, goalY, color);
                if (flag) {
                    return true;
                }
            }

            if (tile.getBottomNeighbor() != null
                    && !visited.contains(tile.getBottomNeighbor())
                    && tile.getBottomNeighbor().getColor() == color) {
                flag = checkTileForPath(tile.getBottomNeighbor(), visited, goalX, goalY, color);
                if (flag) {
                    return true;
                }
            }

            return false;
        }
    }

    // TODO cover case where player can't put a removed piece back in its previous position
    // TODO also check if these are the only conditions with invalid coordinates
    public boolean areValidCoordinates(int[] coordinates, ArrayList<Tile> forbiddenTiles) {
        if (coordinates == null) {
            return false;
        }

        int x = coordinates[0];
        int y = coordinates[1];

        for (Tile t : forbiddenTiles) {
            if (t.getX() == x && t.getY() == y) {
                System.out.println("Not a valid coordinate");
                return false;
            }
        }

        if (x < 0 || x > 6 || y < 0 || y > 6) {
            System.out.println("Not a valid coordinate");
            return false;
        } else if (board[x][y] != null) {
            System.out.println("Not a valid coordinate");
            return false;
        } else {
            System.out.println("Great! These are valid coordinates.");
            return true;
        }
    }

    public String toString() {
        String s = "";
        s += "  0 0 0 0 0 0 0\n";
        for (int i = 6; i >= 0; i--) { // print top-down
            s += "1 ";
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == null) {
                    s += "_ ";
                } else {
                    s += board[i][j].getColor() + " ";
                }

                if (j == 6) {
                    s += "1\n";
                }
            }
        }
        s += "  0 0 0 0 0 0 0\n";
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