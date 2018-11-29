import java.util.Scanner;
import java.util.Arrays;

public class Player {
    private boolean isMyTurn;
    private int tilesRemaining;
    private int color;

    public Player(int color) {
        isMyTurn = false;
        tilesRemaining = 14;
        this.color = color;
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

    public int getColor() {
        return this.color;
    }

    public Tile makeMove(Board currentBoard, int userColor) { // TODO call with these args
        if (tilesRemaining == 0) {
            return null;
        } else {
            tilesRemaining--;
        }

        int[] coordinates = null;
        while (!areValidCoordinates(coordinates, currentBoard)) {
            coordinates = pollForCoordinates();
        }

        return new Tile(userColor, coordinates[0], coordinates[1]);
    }

    private boolean areValidCoordinates(int[] coordinates, Board board) {
        return board.areValidCoordinates(coordinates);
    }

    private int[] pollForCoordinates() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter coordinates for your next move (format as 'x,y').\n" +
                "Note that if your coordinates are invalid, you will be asked again.");
        String[] str_coordinates = scanner.next().split(",");
        int[] coordinates = new int[2];
        coordinates[0] = Integer.valueOf(str_coordinates[0]);
        coordinates[1] = Integer.valueOf(str_coordinates[1]);
        scanner.close();
        return coordinates;
    }

    public static void main(String[] args) {

    }
}