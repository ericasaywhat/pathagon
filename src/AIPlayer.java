import java.util.ArrayList;

/*
* This class contains all the algorithms for the AI implementation.
*
* Currently the algorithm implemented is Minimax. Alpha-Beta pruning
* will be the next step of the implementation and should only be an
* addition of two variables into the evaluation function, the alpha
* and the beta as well as some comparisons so that we don't have to
* generate any branches that we know is a worse choice.
*
* */

public class AIPlayer extends Player {
    private Node tree;
    private Node currentTurn;
    private Tile lastTilePlaced;


    public AIPlayer() {
        super(1);
    }

    /*
     * printTree is a function that takes a node
     * and uses printGivenLevel as a helper function
     * that recurses within itself to iterate through
     * and print all the node's children and descendants
     *
     * */
    public void printTree(Node node) {
        int h = node.getDepth();
        int i;
        for (i=1; i<=h; i++)
            printGivenLevel(node, i);
    }

    private void printGivenLevel (Node node ,int level)
    {
        if (node == null)
            return;
        if (level == 1)
            System.out.print(node.getValue() + " ");
        else if (level > 1)
        {
            for(Node child : node.getChildren()) {
                printGivenLevel(child, child.getDepth());
            }
        }
    }

    /*
     *  nextPlayerTier is a function that returns all the next steps
     *  of the other player based on the last tile the player
     *  placed and currently assumes that the pieces have to
     *  be touching.
     *
     * */
    private ArrayList<Node> nextPlayerTier(Board board, Node aiMove) {
        Tile tile = board.getLastPlayerTilePlaced();
        int x = tile.getX();
        int y = tile.getY();
        Tile[][] gameBoard = board.getBoard();

        ArrayList<Node> nextTier = new ArrayList<>();

        if (y + 1 < 7 && gameBoard[x][y + 1] == null) {
            Node newPlayerNode = new Node(aiMove, aiMove.getDepth() + 1,
                    new int[]{x, y + 1}, false);
            nextTier.add(newPlayerNode);

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

        System.out.println(board);

        return nextTier;
    }

    /*
     *  nextAITier is a function that returns all the next steps
     *  of the AI for each of its possible turns
     *  based on the last tile the player
     *  placed and currently assumes that the pieces have to
     *  be touching.
     *
     * */
    private ArrayList<Node> nextAITier(Board board, Node aiMove, Node playerMove) {
        int x = aiMove.getCoords()[0];
        int y = aiMove.getCoords()[1];
        Tile tile = new Tile(1, x, y);

        Tile[][] gameBoard = board.getBoard();

        ArrayList<Node> nextTier = new ArrayList<>();

        if (y + 1 < 7 && gameBoard[x][y + 1] == null) {
            nextTier.add(new Node(playerMove, playerMove.getDepth() + 1,
                    new int[]{x, y + 1}, true));
        }
        if (y - 1 >= 0 && gameBoard[x][y - 1] == null) {
            nextTier.add(new Node(playerMove, playerMove.getDepth() + 1,
                    new int[]{x, y - 1}, true));
        }
        if (x + 1 < 7 && gameBoard[x + 1][y] != null) {
            nextTier.add(new Node(playerMove, playerMove.getDepth() + 1,
                    new int[]{x + 1, y}, true));
        }
        if (x - 1 >= 0 && gameBoard[x - 1][y] != null) {
            nextTier.add(new Node(playerMove, playerMove.getDepth() + 1,
                    new int[]{x - 1, y}, true));
        }

        return nextTier;
    }

    /*
     * The following function is the evaluation function that we use
     * to evaluate each move. This function currently is an offensive
     * strategy and only looks at whether or not a path can be made.
     *
     * If the AI can make a path, then the node is set to a value of 10000.
     * Otherwise if the other player can make a path then the node is set
     * to a value of -10000
     *
     * Next steps for this function is to add alpha and beta variables that
     * will be taken as inputs and passed through recursively up the game tree.
     * The value must lie between alpha and beta in order to be a possible
     * branch. If not, the function should not bother generating that branch.
     *
     * Further steps for this function could be to weigh success differently,
     * having better moves or worse moves, rather than just a binary good move (10000)
     * and bad move (-100000).
     * */

    private Node evaluationHelper(Board board, Node node, Player otherPlayer) {
        if (this.getTilesRemaining() == 0 || board.isPathMade() != -1) {
            return node;
        }

        Tile tile = new Tile(1, node.getCoords()[0], node.getCoords()[1]);
        board.placeTile(tile);
        board.checkForTrap(tile, otherPlayer);
        int result = board.findLongestPath();
        if (board.isPathMade() == 0) {
            node.setValue(-10000);
        } else if (board.isPathMade() == 1) {
            node.setValue(10000);
        } else {
            int value = board.findLongestPath();
            node.setValue(value);
        }

        node.setChildren(nextPlayerTier(board, node));

        for (Node child : node.getChildren()) {
            ArrayList<Node> grandchildren = nextAITier(board, node, child);
            child.setChildren(grandchildren);
            evaluationHelper(board, child, otherPlayer);
            int minValueSoFar = 10000;
            for (Node grandchild : grandchildren) {
                if (grandchild.getValue() < minValueSoFar) {
                    minValueSoFar = grandchild.getValue();
                }
            }
            child.setValue(minValueSoFar);
        }

        int maxValueSoFar = -10000;
        for (Node child : node.getChildren()) {
            if (child.getValue() > maxValueSoFar) {
                maxValueSoFar = child.getValue();
            }
        }
        node.setValue(maxValueSoFar);

        return node;
    }

    private void generateTree(Player otherPlayer, Tile opponentMove) {
        //TODO accomodate for player going first
        currentTurn = new Node(null, 0, null, true);
        currentTurn.setValue(0);
        for (int y = 0; y < 7; y++) {
            Board newBoard = new Board();
            newBoard.placeTile(opponentMove);
            Node root = evaluationHelper(newBoard, new Node(null, 0, new int[]{0, y}, true), otherPlayer); // generate a new board for each starter
            if (root.getValue() > currentTurn.getValue()) {
                this.currentTurn = root;
                this.tree = root;
            }
        }
    }

    /*
    * The following function returns a tile that is the AI's move.
    *
    * It takes in the opponent's move and finds the node in the children
    * of its last turn and then finds the best move which should have the
    * same value as the parent node since the values are passed up in
    * tree generation and returns that move as the AI's move.
    *
    * */

    public Tile makeMove(Tile opponentMove, Player otherPlayer) {
        generateTree(otherPlayer, opponentMove);

        for (Node child : currentTurn.getChildren()) {
            if (child.getCoords()[0] == opponentMove.getX() &&
                    child.getCoords()[1] == opponentMove.getY()) {
                for (Node grandchild : child.getChildren()) {
                    if (grandchild.getValue() == child.getValue()) {
                        currentTurn = grandchild;
                    }
                }
                break;
            }
        }

        int[] coords = currentTurn.getCoords();
        lastTilePlaced = new Tile(getColor(), coords[0], coords[1]);

        return lastTilePlaced;
    }

    public static void main(String[] args) {
    }
}
