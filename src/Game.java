public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int numberOfTurns;
    private boolean isGameOver;

    public Game() {
        board = new Board();
        player1 = new Player();
        player2 = new Player();
        currentPlayer = player1;
        isGameOver = false;
    }

    public void play() {
        while (!isGameOver) {
            Tile tile = currentPlayer.makeMove();
            board.placeTile(tile);
            checkForTrap();
            checkIsGameOver();
        }
        System.out.println("Game over!");
    }

    public void checkForTrap() {
        // TODO figure out if there are any traps from last move
        // TODO if so, return tile to appropriate player, empty the spot, and clear neighbors
    }

    public void checkIsGameOver() {
        // TODO ask the board if game is over lol
    }

    public void switchCurrentPlayer() {
        currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
    }

    public static void main(String[] args) {

    }
}
