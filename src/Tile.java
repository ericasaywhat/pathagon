public class Tile {
    private int color;
    private Tile leftNeighbor;
    private Tile rightNeighbor;
    private Tile topNeighbor;
    private Tile bottomNeighbor;
    private int x;
    private int y;

    public Tile(int color) {
        this.color = color;
        this.leftNeighbor = null;
        this.rightNeighbor = null;
        this.topNeighbor = null;
        this.bottomNeighbor = null;
    }

    public Tile(int color, int x, int y) {
        this.color = color;
        this.leftNeighbor = null;
        this.rightNeighbor = null;
        this.topNeighbor = null;
        this.bottomNeighbor = null;
        this.x = x;
        this.y = y;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setLeftNeighbor(Tile tile) {
        this.leftNeighbor = tile;
    }

    public void setRightNeighbor(Tile tile) {
        this.rightNeighbor = tile;
    }

    public void setTopNeighbor(Tile tile) {
        this.topNeighbor = tile;
    }

    public void setBottomNeighbor(Tile tile) {
        this.bottomNeighbor = tile;
    }

    public Tile getLeftNeighbor() {
        return this.leftNeighbor;
    }

    public Tile getRightNeighbor() {
        return rightNeighbor;
    }

    public Tile getTopNeighbor() {
        return topNeighbor;
    }

    public Tile getBottomNeighbor() {
        return bottomNeighbor;
    }

    public Tile[] getAllNeighbors() {
        Tile[] neighbors = new Tile[4];
        neighbors[0] = leftNeighbor;
        neighbors[1] = rightNeighbor;
        neighbors[2] = bottomNeighbor;
        neighbors[3] = topNeighbor;
        return neighbors;
    }

    public int getColor() {
        return color;
    }
}