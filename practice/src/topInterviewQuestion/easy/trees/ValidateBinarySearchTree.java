package topInterviewQuestion.easy.trees;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
//https://leetcode.com/problems/validate-binary-search-tree/solution/
public class ValidateBinarySearchTree {

    public static void main(String[] args) {

        TreeNode two = new TreeNode(2);
        two.left = new TreeNode(1);
        two.right = new TreeNode(3);
        System.out.println(isValidBSTIterativeInorderTraversal(two));

    }
    //Approach 4: Iterative Inorder Traversal
    static boolean isValidBSTIterativeInorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Integer prev = null;
        while(!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(prev != null && root.val <= prev)
                return false;
            prev = root.val;
            root = root.right;
        }
        return true;
    }


    //Approach 3: Recursive Inorder Traversal
    /*
    Time complexity: O(N)
    Space complexity: O(N)
     */
    static Integer prev;
    static boolean isValidBSTRecursiveInorderTraversal(TreeNode root) {
        prev = null;
        return inorder(root);
    }
    static boolean inorder(TreeNode root) {
        if(root == null)
            return true;
        if(!inorder(root.left))
            return false;
        if(prev != null && root.val <= prev)
            return false;
        prev = root.val;
        return inorder(root.right);
    }

    static Deque<TreeNode> stack = new LinkedList<>();
    static Deque<Integer> upperLimits = new LinkedList<>();
    static Deque<Integer> lowerLimits = new LinkedList<>();
    //Approach 2: Iterative Traversal Valid Range
    /*
    Time complexity: O(N)
    Space complexity: O(N)
     */
    static boolean isValidBSTIterative(TreeNode root) {
        Integer low = null, high = null, val;
        update(root, low, high);
        while(!stack.isEmpty()) {
            root = stack.poll(); //Equivalent of pollFirst();
            low = lowerLimits.poll();
            high = upperLimits.poll();
            if(root == null)
                continue;
            val = root.val;
            if(low != null && val <= low)
                return false;
            if(high != null && val >= high)
                return false;
            update(root.right, val, high);
            update(root.left, low, val);
        }
        return true;
    }
    private static void update(TreeNode root, Integer low, Integer high) {
        stack.add(root);
        lowerLimits.add(low);
        upperLimits.add(high);
    }

    //Approach 1: Recursive traversal with valid Range
    /*
    Time complexity: O(N)
    Space complexity: O(N)
     */
    static boolean valdiateRecursiveTraversal(TreeNode root) {
        return validate(root,null, null);
    }
    private static boolean validate(TreeNode root, Integer low, Integer high) {
        if(root == null)
            return true;
        if(low != null && root.val <= low
                || high != null && root.val >= high) {
            return false;
        }
        return validate(root.right, root.val, null)
                && validate(root.left, null, root.val);
    }


}
