import java.util.ArrayList;

public class AIPlayer extends Player {
    private Node tree;
    private Node currentTurn;
    private Tile lastTilePlaced;


    public AIPlayer() {
        super(1);
    }

    // TODO print trees

    // function that returns all the next steps of the other player

    /*
     *  This is a function that returns all the next steps
     *  of the other player based on the last tile the player
     *  placed and currently assumes that the pieces have to
     *  be touching.
     *
     * */
    private ArrayList<Node> nextTier(Board board, Node aiMove) {
        Tile tile = board.getLastPlayerTilePlaced();
        int x = tile.getX();
        int y = tile.getY();
        Tile[][] gameBoard = board.getBoard();

        ArrayList<Node> nextTier = new ArrayList<>();

        if (y + 1 < 7 && gameBoard[x][y + 1] == null) {
            nextTier.add(new Node(aiMove, aiMove.getDepth() + 1,
                    new int[]{x, y + 1}, false));
        }
        if (y - 1 >= 0 && gameBoard[x][y - 1] == null) {
            nextTier.add(new Node(aiMove, aiMove.getDepth() + 1,
                    new int[]{x, y - 1}, false));
        }
        if (x + 1 < 7 && gameBoard[x + 1][y] != null) {
            nextTier.add(new Node(aiMove, aiMove.getDepth() + 1,
                    new int[]{x + 1, y}, false));
        }
        if (x - 1 >= 0 && gameBoard[x - 1][y] != null) {
            nextTier.add(new Node(aiMove, aiMove.getDepth() + 1,
                    new int[]{x - 1, y}, false));
        }

        return nextTier;
    }


    private Node evaluationHelper(Board board, Node node) {
        //TODO insert board logic in here
//        Tile tile = currentPlayer.makeMove(board, currentPlayer.getColor());
//        board.placeTile(tile);
//        checkForTrap(tile);
//        System.out.println(board.toString());
//        board.setLastPlayerTilePlaced(tile);
//        isGameOver = checkIsGameOver();
//        switchCurrentPlayer();
        //TODO check if max or min and get max and min of nodes accordingly
        if (this.getTilesRemaining() == 0) {
            return node;
        }
        // get all possible AI moves
        // return either max or min depending on which node
    }

    private void evaluation() {
        //TODO accomodate for player going first
        currentTurn = new Node(null, 0, null, true);
        currentTurn.setValue(0);
        for (int y = 0; y < 7; y++) {
            Node root = evaluationHelper(new Board(), new Node(null, 0,
                    new int[]{0, y}, true)); // generate a new board for each starter
            if (root.getValue() > currentTurn.getValue()) {
                currentTurn = root;
            }
        }
    }


    //Base cases:
    //      Someone wins
    //      No next move; stalemate/draw
    //Evaluation of game state:
    //      If AI wins, evaluated value should be bigger than some positive value
    //      If AI loses, then it should have a value lower than some negative value
    //      If there is a draw, it should have a value between these values
    //      The better position the bigger the value
    //Evaluation might also depend on
    //      How many steps it took to win
    //      How close the opponent was to winning
    //      The better organized this function the better the results

    public Tile AIMove(Tile opponentMove) {
        if (this.tree == null) {
            evaluation();
        }
        int[] coords = currentTurn.getCoords();
        Tile tile = new Tile(getColor(), coords[0], coords[1]);
        // set last tile placed
        //update tree;


        return null;

    }
}

