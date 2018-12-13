public class Game {
    private Board board;
    private Player player1;
    private AIPlayer player2;
    private Player currentPlayer;
    private Player otherPlayer;
    private int numberOfTurns;
    private boolean isGameOver;

    /*
     *  This code is a game of Pathagon. In which
     *  we have one player, player1, and one AI,
     *  player2that uses Minimax and Alpha-Beta pruning
     *  to generate game trees to make moves.
     *
     *  The player/user plays black (0) and
     *  AI is white (1).
     *
     *  Black plays from bottom edge to top edge
     *  White plays from left edge to right edge
     * **/

    public Game() {
        board = new Board();
        player1 = new Player(0);
        player2 = new AIPlayer();
        currentPlayer = player1;
        otherPlayer = player2;
        isGameOver = false;
    }

    public void play() {
        while (!isGameOver) {
            // if ai then pass in gameboard
            Tile tile;
            if (currentPlayer.equals(player2)) {
                tile = player2.makeMove(board.getLastPlayerTilePlaced(), player1);
            } else {
                tile = currentPlayer.makeMove(board, currentPlayer.getColor());
            }
            board.placeTile(tile);
            checkForTrap(tile);
            System.out.println(board.toString());
            board.setLastPlayerTilePlaced(tile);
            isGameOver = checkIsGameOver();
            switchCurrentPlayer();
        }
    }

    /*
     * This function checks the last move to see if it has trapped any
     * of the other player's pieces. If there is a trap, the tile is
     * removed from the board and the other player's number of
     * remaining tiles is incremented.
     *
     * The following conditions qualify for a trap:
     *      - there must be a neighbor of opposite colour
     *      - there must be a neighbor two blocks down of
     *        the same colour
     * */
    public void checkForTrap(Tile tile) {
        int x = tile.getX();
        int y = tile.getY();
        int colour = tile.getColor();
        Tile[][] gameBoard = board.getBoard();

        if (y+1 < 7 && gameBoard[x][y+1] != null && (gameBoard[x][y+1].getColor() != colour)) {
            if (gameBoard[x][y+2] != null && (gameBoard[x][y+2].getColor() == colour)) {
                otherPlayer.addForbiddenTile(gameBoard[x][y+1]);
                board.removeTile(gameBoard[x][y+1]);
                otherPlayer.setTilesRemaining(otherPlayer.getTilesRemaining()+1);
            }
        }
        if (y-1 >= 0 && gameBoard[x][y-1] != null && gameBoard[x][y-1].getColor() != colour) {
            if (gameBoard[x][y-2] != null && (gameBoard[x][y-2].getColor() == colour)) {
                otherPlayer.addForbiddenTile(gameBoard[x][y-1]);
                board.removeTile(gameBoard[x][y-1]);
                otherPlayer.setTilesRemaining(otherPlayer.getTilesRemaining()+1);
            }
        }
        if (x+1 < 7 && gameBoard[x+1][y] != null && gameBoard[x+1][y].getColor() != colour) {
            if (gameBoard[x+2][y] != null && (gameBoard[x+2][y].getColor() == colour)) {
                otherPlayer.addForbiddenTile(gameBoard[x+1][y]);
                board.removeTile(gameBoard[x+1][y]);
                otherPlayer.setTilesRemaining(otherPlayer.getTilesRemaining()+1);
            }
        }
        if (x-1 >= 0 && gameBoard[x-1][y] != null && gameBoard[x-1][y].getColor() != colour) {
            if (gameBoard[x-2][y] != null && (gameBoard[x-2][y].getColor() == colour)) {
                otherPlayer.addForbiddenTile(gameBoard[x-1][y]);
                board.removeTile(gameBoard[x-1][y]);
                otherPlayer.setTilesRemaining(otherPlayer.getTilesRemaining()+1);
            }
        }
    }

    /*
     * This function checks the if the game is over by
     * checking if a path has been made
     *
     * */
    public boolean checkIsGameOver() {
        int result = board.isPathMade();
        if (result != -1) {
            String winner = (result == 0) ? "1" : "2";
            System.out.println("Congratulations, Player " + winner + "! You've won!");
            return true;
        }
        return false;
    }

    public void switchCurrentPlayer() {
        currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
        otherPlayer = (otherPlayer.equals(player2)) ? player1 : player2;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
