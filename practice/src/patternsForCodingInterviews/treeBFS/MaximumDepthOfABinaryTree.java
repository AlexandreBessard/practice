package patternsForCodingInterviews.treeBFS;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfABinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree Maximum Depth: " + findMaxDepth(root));
        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        System.out.println("Tree Maximum Depth: " + findMaxDepth(root));
    }

    /*
    Find the MAXIMUM Depth
    Time: O(N)
    Space O(N)
     */
    public static int findMaxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int maximumDepth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while( ! queue.isEmpty()) {
            int levelSize = queue.size();
            maximumDepth++;
            for(int i = 0; i < levelSize; i++) {
                TreeNode currNode = queue.poll();
                if(currNode.left != null) {
                    queue.add(currNode.left);
                }
                if(currNode.right != null) {
                    queue.add(currNode.right);
                }
            }
        }
        return maximumDepth;
    }
}
