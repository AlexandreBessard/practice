package leetcode.treesAndGraphs;

import leetcode.topinterview150.BFS.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// https://leetcode.com/problems/balanced-binary-tree/description/
public class BalancedBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(isBalanced(root));

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(3);
        root1.left.left.left = new TreeNode(4);
        root1.left.left.right = new TreeNode(4);
        System.out.println(isBalanced(root1));
    }

    static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        // DFS
        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, Integer> heights = new HashMap<>();
        TreeNode curr = root;
        TreeNode prev = null;
        int maxHeightDiff = 0;

        while(curr != null || !stack.isEmpty()) {

            while (curr != null) {
                // Add all left nodes to the stack
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.peek();
            // Check if we have visited the right subtree of the current node, true means yes, proceed
            if (curr.right != null || curr.right == prev) {
                // Get the left height
                int leftHeight = heights.getOrDefault(curr.left, 0);
                // Get the right height
                int rightHeight = heights.getOrDefault(curr.right, 0);
                // Add one level to the current height
                int currentHeight = Math.max(leftHeight, rightHeight) + 1;
                // Set the height to the current level
                heights.put(curr, currentHeight);
                if (Math.abs(leftHeight - rightHeight) > 1) {
                    return false; // Tree is not height-balanced
                    //A height-balanced binary tree is a binary tree in which the depth of the two
                    // subtrees of every node never differs by more than one.
                }
                maxHeightDiff = Math.max(maxHeightDiff, Math.abs(leftHeight - rightHeight));
                // Remove from the stack
                stack.pop();
                prev = curr;
                curr = null;
            } else {
                curr = curr.right;
            }
        }
        return true;
    }

}
