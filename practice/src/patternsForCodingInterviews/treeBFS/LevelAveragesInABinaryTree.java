package patternsForCodingInterviews.treeBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743864804_47Unit
public class LevelAveragesInABinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<Double> result = findLevelAverages(root);
        System.out.print("Level averages are: " + result);
    }

    /*
    Time: O(N)
    Space: O(N)
    Follow up: Find the largest value on each level of a binary Tree
     */
    public static List<Double> findLevelAverages(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if(root == null)
            return result;
        //int maxValue = Integer.MIN_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while( ! queue.isEmpty()) {
            int levelSize = queue.size();
            double levelSum = 0;
            for(int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                // maxValue = Math.max(maxValue, currentNode.val);
                levelSum += currentNode.val;
                if(currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if(currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            result.add(levelSum / levelSize);
        }
        //return maxValue
        return result;
    }
}
