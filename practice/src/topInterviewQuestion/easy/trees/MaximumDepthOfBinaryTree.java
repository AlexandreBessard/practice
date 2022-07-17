package topInterviewQuestion.easy.trees;

import java.util.LinkedList;

public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode three = new TreeNode(3);
        three.left = new TreeNode(9);
        three.right = new TreeNode(20);
        three.right.left = new TreeNode(15);
        three.right.right = new TreeNode(7);

        System.out.println(maxDepthIteration(three));

    }

    //Approach 2: Iteration
    /*
    Time complexity: O(N)
    Space complexity: O(N)
     */
    static int maxDepthIteration(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> depths = new LinkedList<>();
        if (root == null) return 0;

        stack.add(root);
        depths.add(1);

        int depth = 0, current_depth = 0;
        while(!stack.isEmpty()) {
            root = stack.pollLast();
            current_depth = depths.pollLast();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                stack.add(root.left);
                stack.add(root.right);
                depths.add(current_depth + 1);
                depths.add(current_depth + 1);
            }
        }
        return depth;
    }

    //Approach 1: Recursion
    /*
    Time complexity: each node is visited once: O(N) N is the number of nodes.
    Space complexity: If tree not balanced: O(N)
    It tree is balanced: height of tree would be log(N) -> O(log(n))
     */
    static int maxDepthRecursion(TreeNode root) {
        if(root == null)
            return 0;
        else {
            int left_height = maxDepthRecursion(root.left);
            int right_height = maxDepthRecursion(root.right);
            return Math.max(left_height, right_height) + 1;
        }
    }

}
