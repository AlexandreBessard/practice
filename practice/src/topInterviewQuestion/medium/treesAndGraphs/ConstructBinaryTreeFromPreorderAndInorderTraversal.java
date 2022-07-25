package topInterviewQuestion.medium.treesAndGraphs;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/788/
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        buildTree(preorder, inorder);
    }

    static int preorderIndex;
    static Map<Integer, Integer> inorderIndexMap;
    //Approach 1: Recursion
    /*
    Time complexity: O(n)
    Space complexity: O(n)
     */
    static TreeNode buildTree(int[] preorder, int[] inorder) {
        int preorderIndex = 0;
        inorderIndexMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return arrayToTree(preorder, 0, preorder.length - 1);
    }

    private static TreeNode arrayToTree(int[] preorder,
                                        int left,
                                        int right) {
        // if there are no elements to construct the tree
        if (left > right) return null;
        // select the preorder_index element as the root and increment it
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);
        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder,
                left,
                inorderIndexMap.get(rootValue) - 1);
        root.right = arrayToTree(preorder,
                inorderIndexMap.get(rootValue) + 1,
                right);
        return root;
    }

}