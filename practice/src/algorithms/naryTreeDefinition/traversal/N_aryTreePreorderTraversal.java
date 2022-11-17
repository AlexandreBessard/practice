package algorithms.naryTreeDefinition.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/explore/learn/card/n-ary-tree/130/traversal/925/
//Preorder -> Node, left right
//A->B->C->E->F->D->G.
public class N_aryTreePreorderTraversal {

    public static void main(String[] args) {
        Node one = new Node(1);
        Node three = new Node(3);
        Node two = new Node(2);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        one.children.addAll(List.of(three, two, four));
        three.children.addAll(List.of(five, six));
        for (int el : preorderRecursive(one)) {
            System.out.print(el + ", ");
        }
        System.out.println();
        for (int el : preorderIterative(one)) {
            System.out.print(el + ", ");
        }
    }

    //Iterative
    /*
    Time: O(n)
    Space: (n)
     */
    static List<Integer> preorderIterative(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.val);
            for (int i = root.children.size() - 1; i >= 0; i--) { //Start by the end (left) for the stack
                stack.add(root.children.get(i)); // from left to right, stack will pop() right in priority
            }
        }
        return list;
    }

    //Recursive (root, left, right)
    /*
    Time: O(n)
    Space: O(n)
     */
    static List<Integer> list = new ArrayList<>();

    static List<Integer> preorderRecursive(Node root) {
        if (root == null)
            return list;
        list.add(root.val);
        for (Node node : root.children) {
            preorderRecursive(node);
        }
        return list;
    }

}
