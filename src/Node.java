import java.util.ArrayList;

public class Node {
    private Node parent;
    private ArrayList<Node> children;
    private int value;
    private int depth;
    private int[] coords;
    private boolean isMax;

    public Node(Node parent, int depth, int[] coords, boolean isMax) {
        this.parent = parent;
        this.depth = depth;
        this.coords = coords;
        this.isMax = isMax;
    }

    public Node getParent() {
        return parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDepth() {
        return depth;
    }

    public int[] getCoords() {
        return coords;
    }

    public boolean getIsMax() {
        return isMax;
    }

}
