package algorithms.binarySearch;

import java.util.*;

//https://leetcode.com/explore/learn/card/binary-search/136/template-analysis/1028/
public class ClosestBinarySearchTreeValue {

    public static void main(String[] args) {
        double target = 3.714286;
        TreeNode four = new TreeNode(4);
        four.left = new TreeNode(2);
        four.right = new TreeNode(5);
        four.left.left = new TreeNode(1);
        four.left.right = new TreeNode(3);
        System.out.println(closestValue(four, 2));
        System.out.println(closestValueRecursiveInorder(four, 2));
        System.out.println(closestValueIterativeInorder(four, 3.71));
    }

    //Approach 2: Iterative Inorder
    /*
    Time: O(k) in the average and O(H + k) in the worst case if unbalanced tree
    Space: O(H) to keep the stack
     */
    static int closestValueIterativeInorder(TreeNode root, double target) {
        Stack<TreeNode> stack = new Stack<>();
        long pred = Long.MIN_VALUE;
        while(!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            if(pred <= target && target < root.val) {
                return Math.abs(pred - target) < Math.abs(root.val - target)
                        ? (int)pred
                        : root.val;
            }
            pred = root.val;
            root = root.right;
        }
        return (int) pred;
    }

    //Approach 1: Recursive Inorder + Linear Search
    /*
    Time: O(N)
    Space: O(N)
     */
    static double closestValueRecursiveInorder(TreeNode root, double target) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        return Collections.min(nums, Comparator.comparingDouble(o -> Math.abs(o - target)));
    }
    static void inorder(TreeNode root, List<Integer> nums) {
        if(root == null)
            return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    //Binary Search
    public static int closestValue(TreeNode root, double target) {
        int val, closest = root.val;
        while (root != null) {
            val = root.val;
            closest = Math.abs(val - target) < Math.abs(closest - target)
                    ? val
                    : closest;
            root =  target < root.val ? root.left : root.right;
        }
        return closest;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

}
