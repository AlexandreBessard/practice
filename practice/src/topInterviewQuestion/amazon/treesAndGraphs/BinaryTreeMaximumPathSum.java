package topInterviewQuestion.amazon.treesAndGraphs;
//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2981/
public class BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        TreeNode minusTen = new TreeNode(-10);
        minusTen.left = new TreeNode(9);
        minusTen.right = new TreeNode(20);
        minusTen.right.left = new TreeNode(15);
        minusTen.right.right = new TreeNode(7);
        System.out.println(maxPathSum(minusTen));
    }

    //Approach 1: Recursion
    /*
    Post order traversal tree -> Left, right, node
    Time complexity: O(N)
    Space complexity: O(N)
     */
    static int maxValue;
    static int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathSumRecursive(root);
        return maxValue;
    }
    //Preorder -> Left, Right, Node
    private static int maxPathSumRecursive(TreeNode node) {
        if(node == null)
            return 0;
        int left = Math.max(0, maxPathSumRecursive(node.left));
        int right = Math.max(0, maxPathSumRecursive(node.right));
        maxValue = Math.max(maxValue, left + right + node.val); //Get the max value from with left, right and Node combined
        //we cannot choose both left and right brunches, we select the largest one to prune the lower.
        return Math.max(left, right) + node.val;
    }

}
