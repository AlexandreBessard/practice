package patternsForCodingInterviews.treeBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743859019_46Unit
public class ZigzagTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(17);
        List<List<Integer>> result = traverse(root);
        System.out.println(result);
    }

    /*
    Time: O(N)
    Space: O(N
     */
    public static List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        while( ! queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new LinkedList<>();
            // LinkedList<Integer> currentLevel = new LinkedList<>();
            for(int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                //Add the node to the current Level
                if(leftToRight) {
                    currentLevel.add(currentNode.val);
                    // currentLevel.addLast(current.val);
                } else {
                    currentLevel.add(0, currentNode.val);
                    // currentLevel.addFirst(current.val);
                }
                //Insert children
                if(currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if(currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(currentLevel);
            leftToRight =  ! leftToRight;
        }
        return result;
    }
}
