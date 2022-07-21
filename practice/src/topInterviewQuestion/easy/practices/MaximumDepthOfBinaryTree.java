package topInterviewQuestion.easy.practices;

import java.util.LinkedList;

public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {

    }



    static int maxDepthIterative(TreeNode node) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> depths = new LinkedList<>();
        stack.add(node);
        depths.add(1);
        int maxDepth = 0;
        while(!stack.isEmpty()) {
            TreeNode curr = stack.pollLast();
            int currDepth = depths.pollLast();
            if(curr != null) {
                maxDepth = Math.max(maxDepth, currDepth);
                stack.add(curr.left);
                stack.add(curr.right);
                depths.add(currDepth + 1);
                depths.add(currDepth + 1);
            }
        }
        return maxDepth;
    }


    /*
    Time complexity: O(N)
    Space complexity: O(N) if each node has 1 child
    If balanced, O(log(N))
     */
    static int maxDepth(TreeNode node) {
        if(node == null)
            return 0;
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return Math.max(left, right) + 1;
    }

}
