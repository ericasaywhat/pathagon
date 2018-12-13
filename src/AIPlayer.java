import java.util.ArrayList;

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

        return nextTier;
    }

    /*
     *  nextAITier is a function that returns all the next steps
     *  of the other player based on the last tile the player
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

    private void evaluation(Player otherPlayer) {
        //TODO accomodate for player going first
        currentTurn = new Node(null, 0, null, true);
        currentTurn.setValue(0);
        for (int y = 0; y < 7; y++) {
            Node root = evaluationHelper(new Board(), new Node(null, 0, new int[]{0, y}, true), otherPlayer); // generate a new board for each starter
            if (root.getValue() > currentTurn.getValue()) {
                currentTurn = root;
                tree = root;
            }
        }
    }

    public Tile AIMove(Tile opponentMove, Player otherPlayer) {
        if (this.tree == null) {
            evaluation(otherPlayer);
        }

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
