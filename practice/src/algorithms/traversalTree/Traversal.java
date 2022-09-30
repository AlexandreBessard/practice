package algorithms.traversalTree;

import java.util.ArrayList;
import java.util.List;

public class Traversal {
    /*
    Inoder: Left, Node, Right
    PreOrder: Node, Left Right
    PostOrder: Left, Right, Node
     */
    public static void main(String[] args) {
        var one = new TreeNode(1);
        one.left = new TreeNode(2);
        one.right = new TreeNode(3);
        List<Integer> res = new ArrayList<>();
        PreOrder.preOrderTraversal(one, res);
        System.out.println("\n Post Order Traversal");
        for (int i : res) {
            System.out.print(i + ", ");
        }
        InOrder.inOrderTraversal(one, res = new ArrayList<>());
        System.out.println("\n In Order Traversal");
        for (int i : res) {
            System.out.print(i + ", ");
        }
        PostOrder.postOrderTraversal(one, res = new ArrayList<>());
        System.out.println("\n Post-Order Traversal");
        for (int i : res) {
            System.out.print(i + ", ");
        }
    }

    static class PreOrder {
        /*
        - Visit the root
        - Traverse left subtree
        - Traverse right subtree
         */
        private static void preOrderTraversal(TreeNode root,
                                              List<Integer> answer) {
            if (root == null)
                return;
            answer.add(root.val);
            preOrderTraversal(root.left, answer);
            preOrderTraversal(root.right, answer);
        }

    }

    /*
    - Traverse left subtree
    - Visit root
    - Traverse right subtree
     */
    public static class InOrder {
        private static void inOrderTraversal(TreeNode root,
                                             List<Integer> answer) {
            if (root == null)
                return;
            inOrderTraversal(root.left, answer);
            answer.add(root.val);
            inOrderTraversal(root.right, answer);
        }
    }

    public static class PostOrder {
        /*
        - Traverse left subtree
        - Traverse right subtree
        - visit the node
         */
        private static void postOrderTraversal(TreeNode root,
                                               List<Integer> answer) {
            if (root == null)
                return;
            postOrderTraversal(root.left, answer);
            postOrderTraversal(root.right, answer);
            answer.add(root.val);
        }
    }

}

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}
