package patternsForCodingInterviews.treeDFS;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743967658_60Unit
public class _2PathWithMaximumSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println("Maximum Path Sum: " + findMaximumPathSum(root));

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        System.out.println("Maximum Path Sum: " + findMaximumPathSum(root));

        root = new TreeNode(-1);
        root.left = new TreeNode(-3);
        System.out.println("Maximum Path Sum: " + findMaximumPathSum(root));
    }

    private static int globalMaximumSum;
    public static int findMaximumPathSum(TreeNode root) {
        globalMaximumSum = Integer.MAX_VALUE;
        findMaximumPathSumRecursive(root);
        return globalMaximumSum;
    }
    private static int findMaximumPathSumRecursive(TreeNode currentNode) {
        if(currentNode == null)
            return 0;
        int maxSumFromLeft = findMaximumPathSumRecursive(currentNode.left);
        int maxSumFromRight = findMaximumPathSumRecursive(currentNode.right);
        //Ignore paths with negative sums.
        maxSumFromLeft = Math.max(maxSumFromLeft, 0);
        maxSumFromRight = Math.max(maxSumFromRight, 0);
        //max path sum at the current node
        int localMaximumSum = maxSumFromLeft + maxSumFromRight + currentNode.val;
        //Update the global maximum
        globalMaximumSum = Math.max(globalMaximumSum, localMaximumSum);
        return Math.max(maxSumFromLeft, maxSumFromRight) + currentNode.val;
    }

}
