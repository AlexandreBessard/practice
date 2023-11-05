package GrokkingCodingInterviewPatterns.TwoPointers.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        var two = new TreeNode(2);
        var three = new TreeNode(3);
        var four = new TreeNode(4);
        var five = new TreeNode(5);
        var six = new TreeNode(6);
        var seven = new TreeNode(7);
        one.left = two;
        one.right = three;
        one.left.left = four;
        one.left.right = five;
        one.right.left = six;
        one.right.right = seven;
        List<List<Integer>> result = traverse(one);
        // Loop through the list of lists
        for (List<Integer> innerList : result) {
            // Loop through the integers within each inner list
            for (Integer number : innerList) {
                System.out.print(number + " ");
            }
            System.out.println(); // Start a new line for each inner list
        }
    }

    static List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                // add the node to the current level
                currentLevel.add(currentNode.val);
                // insert the children of current node in the queue
                if (currentNode.left != null)
                    queue.offer(currentNode.left);
                if (currentNode.right != null)
                    queue.offer(currentNode.right);
            }
            result.add(currentLevel);
        }

        return result;
    }

}
