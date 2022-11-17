package algorithms.naryTreeDefinition.traversal;

import java.util.ArrayList;
import java.util.List;

class Node {
    int val;
    List<Node> children = new ArrayList<>();
    Node(){}
    Node(int val) {
        this.val = val;
    }
    Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
