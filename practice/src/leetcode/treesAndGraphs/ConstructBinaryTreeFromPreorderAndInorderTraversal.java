package leetcode.treesAndGraphs;

import topInterviewQuestion.medium.treesAndGraphs.TreeNode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/788/
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
/*
Given two integer arrays preorder and inorder where
preorder is the preorder traversal of a binary tree and inorder
is the inorder traversal of the same tree, construct and return the binary tree.
 */
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        buildTree(preorder, inorder);
    }

    static int preorderIndex = 0;
    static Map<Integer, Integer> inorderIndexMap;
    //Approach 1: Recursion
    //Explanation for better understanding : https://www.youtube.com/watch?v=ihj4IQGZ2zc
    /*
    Time complexity: O(n)
    Space complexity: O(n)
     */
    //Preorder: Node, Left, Right \\ Inorder: Left, Node, Right
    static topInterviewQuestion.medium.treesAndGraphs.TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderIndexMap = new HashMap<>(); //Create a HashMap
        for(int i = 0; i < inorder.length; i++) {
            //Key: value, Value: index
            inorderIndexMap.put(inorder[i], i);
        }
        return arrayToTree(preorder, 0, preorder.length - 1);
    }

    private static topInterviewQuestion.medium.treesAndGraphs.TreeNode arrayToTree(int[] preorder,
                                                                                   int left,
                                                                                   int right) {
        // if there are no elements to construct the tree
        if (left > right) return null;
        // select the preorder_index element as the root and increment it
        int rootValue = preorder[preorderIndex++];
        topInterviewQuestion.medium.treesAndGraphs.TreeNode root = new TreeNode(rootValue);
        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1); //Construct left subtree
        root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right); //Construct right subtree
        return root;
    }

}
