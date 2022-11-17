package topInterviewQuestion.topInterviewQuestions.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/explore/featured/card/top-interview-questions-easy/94/trees/628/
public class BinaryTreeLevelOrderTraversal {
    /*
    Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
     */
    public static void main(String[] args) {
        TreeNode three = new TreeNode(3);
        three.left = new TreeNode(9);
        three.right = new TreeNode(20);
        three.right.left = new TreeNode(15);
        three.right.right = new TreeNode(7);
        System.out.println(levelOrderRecursive(three));
    }


    //Approach 1: Iterative
    /*
    BFS logic
    Time complexity: O(N)
    Space complexity: O(N)
     */
    static List<List<Integer>> levelOrderIterative(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        while (!q.isEmpty()) {
            result.add(new ArrayList<>());
            int levelSize = q.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.remove(); //Throws exception if null element. poll() return null (no exception)
                result.get(level).add(node.val);
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            level++;
        }
        return result;
    }


    //Approach 1: Recursion
    /*
    Time complexity: O(N)
    Space complexity: O(N)
     */
    static List<List<Integer>> levels = new ArrayList<>();

    static List<List<Integer>> levelOrderRecursive(TreeNode root) {
        if (root == null)
            return levels;
        helper(root, 0);
        return levels;
    }

    static void helper(TreeNode node, int level) {
        if (levels.size() == level)
            levels.add(new ArrayList<>());

        levels.get(level).add(node.val);

        if (node.left != null) {
            helper(node.left, level + 1);
        }
        if (node.right != null) {
            helper(node.right, level + 1);
        }
    }
}
