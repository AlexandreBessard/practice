package patternsForCodingInterviews.treeBFS;

import java.util.LinkedList;
import java.util.Queue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743873257_48Unit
public class MinimumDepthOfABinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println(findDepth(root));
        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        System.out.println(findDepth(root));
    }

    /*
    Time: O(N)
    Space: O(N)
     */
    public static int findDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int minimumDepth = 0;
        while( ! queue.isEmpty()) {
            minimumDepth++;
            int levelSize = queue.size();
            for(int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                //Check if this is a leaf node
                if(currentNode.left != null && currentNode.right != null)
                    return minimumDepth; //Condition true, stop the program we have our minimum level

                if(currentNode.left != null)
                    queue.add(currentNode.left);

                if(currentNode.right != null)
                    queue.add(currentNode.right);
            }
        }
        return minimumDepth;
    }
}
