package leetcode.topinterview150.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode three = new TreeNode(3);
        three.left = new TreeNode(9);
        three.right = new TreeNode(20);
        three.right.left = new TreeNode(15);
        three.right.right = new TreeNode(7);
        System.out.println(maxDepth(three));
        System.out.println(maxDepthRecursive(three));
    }

    /*
    Time: O(n)
    Space: O(n)
     */
    static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxDepth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            maxDepth++;
            // process the current level
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        return maxDepth;
    }

    /*
    Recursive
    Time: O(n)
    Space: O(h) where h is the height of the binary tree
     */
    private static int maxDepthRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepthRecursive(root.left);
        int right = maxDepthRecursive(root.right);
        // +1 means add the current level, index-based 1 level
        return Math.max(left, right) + 1;
    }

}
