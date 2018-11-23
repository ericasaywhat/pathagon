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

    public void placeTile() {
        if (tilesRemaining == 0) {
            // TODO fill in
        } else {
            tilesRemaining--;
        }
    }
}