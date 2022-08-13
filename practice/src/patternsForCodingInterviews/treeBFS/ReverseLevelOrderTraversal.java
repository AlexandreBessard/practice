package patternsForCodingInterviews.treeBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743852073_45Unit
public class ReverseLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<List<Integer>> result = traverse(root);
        System.out.println("Reverse level order traversal: " + result);
    }

    /*
    Time: O(N)
    Space: O(N)
     */
    public static List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while( ! queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for(int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                //add node to the current level
                currentLevel.add(currentNode.val);
                if(currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if(currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            //append the current level at the beginning
            result.add(0, currentLevel);
        }
        return result;
    }
}
