public class Player {
    private boolean isMyTurn;
    private int tilesRemaining;

    public Player() {
        isMyTurn = false;
        tilesRemaining = 14;
    }

    public boolean getIsMyTurn() {
        return isMyTurn;
    }

    public void setIsMyTurn(boolean turn) {
        isMyTurn = turn;
    }

    public Tile makeMove() {
        // TODO poll user for coordinates
        // then form new tile with coordinates and color
        // then return tile
        if (tilesRemaining == 0) {
            // TODO fill in
        } else {
            tilesRemaining--;
        }
        return null;
    }
}