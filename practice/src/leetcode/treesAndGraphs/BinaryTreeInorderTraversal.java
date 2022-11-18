package leetcode.treesAndGraphs;

import topInterviewQuestion.medium.treesAndGraphs.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/786/
public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        topInterviewQuestion.medium.treesAndGraphs.TreeNode one = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(1);
        one.left = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(2);
        one.right = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(3);
        inorderTraversalIteative(one);
    }

    //Approach  1: Recursive inorder traversal
    /*
    Time complexity: O(n)
    Space complexity: O(n)
     */
    static List<Integer> inorderTraversal(topInterviewQuestion.medium.treesAndGraphs.TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private static void helper(TreeNode root, List<Integer> res) {
        //Base case
        if (root == null) {
            return;
        }
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }

    //Approach 2: Iterative, Inorder traversal order
    /*
    Time complexity: O(n)
    Space complexity: O(n)
     */
    static List<Integer> inorderTraversalIteative(topInterviewQuestion.medium.treesAndGraphs.TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<topInterviewQuestion.medium.treesAndGraphs.TreeNode> stack = new Stack<>();
        topInterviewQuestion.medium.treesAndGraphs.TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}
