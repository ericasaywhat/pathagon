import java.util.ArrayList;

public class AIPlayer extends Player {
    private Node tree;

    public AIPlayer() {
        super(1);
    }

    // TODO print trees

    // function that returns all the next steps of the other player


    private ArrayList<Node> nextTier(Board board, Node aiMove) {
        /* This is a function that returns all the next steps
        *  of the other player based on the last tile the player
        *  placed and currently assumes that the pieces have to
        *  be touching.
        * */

        Tile[] neighbors = board.getLastPlayerTilePlaced().getAllNeighbors();
        ArrayList<Node> nextTier = new ArrayList<Node>();

        for(Tile neighbor : neighbors) {
            nextTier.add(new Node(aiMove, aiMove.getDepth()+1, new int[]{neighbor.getX(),neighbor.getY()}, false));
        }

        return nextTier;
    }


    private Node evaluationHelper (Board board, Node node) {

        //if no next move, return value
        // else

        return null;
    }

    public void evaluation (Board board){
        Node maxTree;
        //iterate through first 7, call helper
        //if
        //set biggest root as tree
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

    //print trees

    //root of node is depth 0

    public Tile AIMove(Board board) {
        if (this.tree == null) {  evaluation(board);  }
        //get the coords of the root of the path

        return null;

    }

}

//
//
//// A simple java program to find maximum score that
//// maximizing player can get.
//
//import java.io.*;
//
//class GFG {
//
//
//    // Returns the optimal value a maximizer can obtain.
//// depth is current depth in game tree.
//// nodeIndex is index of current node in scores[].
//// isMax is true if current move is of maximizer, else false
//// scores[] stores leaves of Game tree.
//// h is maximum height of Game tree
//
// static int minimax(int depth, int nodeIndex, boolean  isMax,
//                       int scores[], int h)
//    {
//        // Terminating condition. i.e leaf node is reached
//        if (depth == h)
//            return scores[nodeIndex];
//
//        // If current move is maximizer, find the maximum attainable
//        // value
//        if (isMax)
//            return Math.max(minimax(depth+1, nodeIndex*2, false, scores, h),
//                    minimax(depth+1, nodeIndex*2 + 1, false, scores, h));
//
//            // Else (If current move is Minimizer), find the minimum
//            // attainable value
//        else
//            return Math.min(minimax(depth+1, nodeIndex*2, true, scores, h),
//                    minimax(depth+1, nodeIndex*2 + 1, true, scores, h));
//    }
//
//    // A utility function to find Log n in base 2
//    static int log2(int n)
//    {
//        return (n==1)? 0 : 1 + log2(n/2);
//    }
//
//// Driver code
//
//    public static void main (String[] args) {
//        // The number of elements in scores must be
//        // a power of 2.
//        int scores[] = {3, 5, 2, 9, 12, 5, 23, 23};
//        int n = scores.length;
//        int h = log2(n);
//        int res = minimax(0, 0, true, scores, h);
//        System.out.println( "The optimal value is : "  +res);
//
//    }
//}
//
//// This code is contributed by vt_m