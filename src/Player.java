import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Player {
    private boolean isMyTurn;
    private int tilesRemaining;
    private int color;
    ArrayList<Tile> forbiddenTiles;

    public Player(int color) {
        isMyTurn = false;
        tilesRemaining = 14;
        this.color = color;
        forbiddenTiles = new ArrayList<Tile>();
    }

    public boolean getIsMyTurn() {
        return isMyTurn;
    }

    public int getTilesRemaining() {
        return tilesRemaining;
    }

    public void setTilesRemaining(int tilesRemaining) {
        this.tilesRemaining = tilesRemaining;
    }

    public void setIsMyTurn(boolean turn) {
        isMyTurn = turn;
    }

    public void addForbiddenTile(Tile forbiddenTile) {
        forbiddenTiles.add(forbiddenTile);
    }

    public int getColor() {
        return this.color;
    }

    public Tile makeMove(Board currentBoard, int userColor) {
        if (tilesRemaining == 0) {
            return null;
        } else {
            tilesRemaining--;
        }

        int[] coordinates = null;
        Scanner scanner = new Scanner(System.in);
        while (!areValidCoordinates(coordinates, currentBoard)) {
            coordinates = pollForCoordinates(scanner);
        }

        forbiddenTiles.clear(); // coords of forbidden tiles can now be used

        return new Tile(userColor, coordinates[0], coordinates[1]);
    }

    private boolean areValidCoordinates(int[] coordinates, Board board) {
        return board.areValidCoordinates(coordinates, forbiddenTiles);
    }

    private int[] pollForCoordinates(Scanner scanner) {
        System.out.println("Please enter coordinates for your next move (format as 'x,y').\n" +
                "Note that if your coordinates are invalid, you will be asked again.");
        String[] str_coordinates = scanner.next().split(",");
        int[] coordinates = new int[2];
        coordinates[0] = Integer.valueOf(str_coordinates[0]);
        coordinates[1] = Integer.valueOf(str_coordinates[1]);
        return coordinates;
    }

    public static void main(String[] args) {

    }
}