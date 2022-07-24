package topInterviewQuestion.medium.treesAndGraphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/786/
public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        one.left = new TreeNode(2);
        one.right = new TreeNode(3);
        inorderTraversalIteative(one);
    }

    //Approach 2: Iterative
    /*
    Time complexity: O(n)
    Space complexity: O(n)
     */
    static List<Integer> inorderTraversalIteative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }


    //Approach  1: Recursive
    /*
    Time complexity: O(n)
    Space complexity: O(n)
     */
    static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root,res);
        return res;
    }
    private static void helper(TreeNode root, List<Integer> res) {
        if(root != null) {
            helper(root.left, res);
            res.add(root.val);
            helper(root.right, res);
        }
    }


}
