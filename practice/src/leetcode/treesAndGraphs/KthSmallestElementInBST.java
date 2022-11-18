package leetcode.treesAndGraphs;

import topInterviewQuestion.medium.treesAndGraphs.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/790/
public class KthSmallestElementInBST {
    /*
    Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed)
    of all the values of the nodes in the tree.
     */
    public static void main(String[] args) {
        topInterviewQuestion.medium.treesAndGraphs.TreeNode five = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(5);
        five.left = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(3);
        five.left.left = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(2);
        five.left.left.left = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(1);
        five.left.right = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(4);
        five.right = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(6);

        topInterviewQuestion.medium.treesAndGraphs.TreeNode five2 = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(5);
        five2.left = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(3);
        five2.left.left = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(2);
        five2.left.right = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(4);
        five2.right = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(6);
        five2.right.right = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(7);

        int k = 5;
        kthSmallestRecursiveIterative(five2, k);
    }


    //Approach 2: Iterative
    /*
    Time complexity: O(H + k) H is the tree height, defined by the stack which contains
    at least H + k elements
    O(log N + k) for balanced tree and O(N + k)  for completely unbalanced tree. (all nodes in the left subtree)
    Space complexity: O(H) to keep the stack where H is the tree height.
    O(N) in the worst case of the skewed tree, else O(log N) in balanced tree.
     */
    static int kthSmallestRecursiveIterative(topInterviewQuestion.medium.treesAndGraphs.TreeNode root, int k) {
        LinkedList<topInterviewQuestion.medium.treesAndGraphs.TreeNode> stack = new LinkedList<>();
        while(true) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(--k == 0)
                return root.val;
            root = root.right;
        }
    }

    //Approach 1: Recursive Inorder Traversal
    /*
    Time complexity: O(n)
    Space complexity: O(n)
     */
    static int kthSmallestRecursive(topInterviewQuestion.medium.treesAndGraphs.TreeNode root, int k) {
        List<Integer> nums = inorder(root, new ArrayList<>());
        return nums.get(k - 1);
    }
    private static List<Integer> inorder(TreeNode root, List<Integer> arr) {
        if(root == null) {
            return arr;
        }
        inorder(root.left, arr); //Left
        arr.add(root.val); //Node
        inorder(root.right, arr); //Right
        return arr;
    }


}
