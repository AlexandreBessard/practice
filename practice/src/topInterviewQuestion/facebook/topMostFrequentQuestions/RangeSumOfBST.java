package topInterviewQuestion.facebook.topMostFrequentQuestions;

import patternsForCodingInterviews.treeBFS.TreeNode;

import java.util.Stack;

//https://leetcode.com/problems/range-sum-of-bst/
public class RangeSumOfBST {

    public static void main(String[] args) {
        TreeNode ten = new TreeNode(10);
        ten.left = new TreeNode(5);
        ten.right = new TreeNode(15);
        ten.left.left = new TreeNode(3);
        ten.left.right = new TreeNode(7);
        ten.right.right = new TreeNode(18);
        System.out.println(rangeSumDFS(ten, 7, 15));
        System.out.println(rangeSumBFS(ten, 7, 15));
    }

    //BFS
    static int rangeSumBFS(TreeNode root, int low, int high) {
        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (low <= node.val && node.val <= high) {
                    ans += node.val;
                }
                if (low < node.val) {
                    stack.push(node.left);
                }
                if (node.val < high) {
                    stack.push(node.right);
                }
            }
        }
        return ans;
    }

    //DFS
    static int ans;

    static int rangeSumDFS(TreeNode root, int low, int high) {
        ans = 0;
        dfs(root, low, high);
        return ans;
    }

    static void dfs(TreeNode node, int low, int high) {
        if (node != null) {
            if (low <= node.val && node.val <= high) { //Tue if node is between low and high
                ans += node.val;
            }
            if (low < node.val) {
                dfs(node.left, low, high);
            }
            if (node.val < high) {
                dfs(node.right, low, high);
            }
        }
    }

}
