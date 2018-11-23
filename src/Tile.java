public class Tile {
    private int color;
    private int timesMoved;
    private boolean isOnBoard;

    public Tile(int color) {
        this.color = color;
        this.timesMoved = 0;
        this.isOnBoard = false;
    }

    public int getColor() {
        return color;
    }

    public void setTileMoved() {
        timesMoved = 2; // to mark countdown; can't be moved for next 2 moves
    }

    public void countdownMoves() {
        timesMoved--;
    }

    public int getTimesMoved() {
        return timesMoved;
    }

    public void setIsOnBoard(boolean isOnBoard) {
        this.isOnBoard = isOnBoard;
    }

    public boolean getIsOnBoard() {
        return isOnBoard;
    }
}