package algorithms.NaryTreeDefinition.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//Postorder -> Left, Right, Node
// B->E->F->C->G->D->A.
public class N_aryTreePostorderTraversal {

    public static void main(String[] args) {
        Node one = new Node(1);
        Node three = new Node(3);
        Node two = new Node(2);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        one.children.addAll(List.of(three, two, four));
        three.children.addAll(List.of(five, six));
        for (int el : postorderRecursive(one)) {
            System.out.print(el + ", ");
        }
        System.out.println();
        for (int el : postorderIterative(one)) {
            System.out.print(el + ", ");
        }
    }

    //Iterative
    /*
    Time: O(n)
    Space: O(n)
     */
    static List<Integer> postorderIterative(Node root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null)
            return list;
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            list.addFirst(node.val);
            for (Node item : node.children) {
                if (item != null) {
                    stack.add(item);
                }
            }
        }
        return list;
    }

    //Recursive
    /*
    Time: O(n)
    Space: O(n)
     */
    static List<Integer> list = new ArrayList<>();

    static List<Integer> postorderRecursive(Node root) {
        if (root == null)
            return null;
        for (Node node : root.children) {
            postorderRecursive(node);
        }
        list.add(root.val);
        return list;
    }


}
