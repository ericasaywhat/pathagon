public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player otherPlayer;
    private int numberOfTurns;
    private boolean isGameOver;

    public Game() {
        board = new Board();
        player1 = new Player();
        player2 = new Player();
        currentPlayer = player1;
        otherPlayer = player2;
        isGameOver = false;
    }

    public void play() {
        while (!isGameOver) {
            Tile tile = currentPlayer.makeMove();
            board.placeTile(tile);
            checkForTrap(tile);
            checkIsGameOver();
            switchCurrentPlayer();
        }
        System.out.println("Game over!");
    }

    public void checkForTrap(Tile tile) {
        /**
         * This function checks the last move to see if it has trapped any
         * of the other player's pieces. If there is a trap, the tile is
         * removed from the board and the other player's number of
         * remaining tiles is incremented.
         *
         * The following conditions qualify for a trap:
         *      - there must be a neighbor of opposite colour
         *      - there must be a neighbor two blocks down of
         *        the same colour
         * **/
        
        int x = tile.getX();
        int y = tile.getY();
        int colour = tile.getColor();
        Tile[][] gameBoard = board.getBoard();

        if (gameBoard[x][y+1] != null && (gameBoard[x][y+1].getColor() != colour)) {
            if (gameBoard[x][y+2] != null && (gameBoard[x][y+2].getColor() == colour)) {
                board.removeTile(tile);
                otherPlayer.setTilesRemaining(otherPlayer.getTilesRemaining()+1);
            }
        }
        if (gameBoard[x][y-1] != null && gameBoard[x][y-1].getColor() != colour) {
            if (gameBoard[x][y-2] != null && (gameBoard[x][y-2].getColor() == colour)) {
                board.removeTile(tile);
                otherPlayer.setTilesRemaining(otherPlayer.getTilesRemaining()+1);
            }
        }
        if (gameBoard[x+1][y] != null && gameBoard[x+1][y].getColor() != colour) {
            if (gameBoard[x+2][y] != null && (gameBoard[x+2][y].getColor() == colour)) {
                board.removeTile(tile);
                otherPlayer.setTilesRemaining(otherPlayer.getTilesRemaining()+1);
            }
        }
        if (gameBoard[x-1][y] != null && gameBoard[x-1][y].getColor() != colour) {
            if (gameBoard[x-2][y] != null && (gameBoard[x-2][y].getColor() == colour)) {
                board.removeTile(tile);
                otherPlayer.setTilesRemaining(otherPlayer.getTilesRemaining()+1);
            }
        }
    }

    public void checkIsGameOver() {
        // TODO ask the board if game is over lol
    }

    public void switchCurrentPlayer() {
        currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
        otherPlayer = (otherPlayer.equals(player2)) ? player1 : player2;
    }

    public static void main(String[] args) {

    }
}
