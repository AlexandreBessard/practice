package topInterviewQuestion.amazon.treesAndGraphs;
//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2981/
public class BinaryTreeMaximumPathSum {

    public static void main(String[] args) {



    }

    //Approach 1: Recursion
    /*
    Post order traversal tree -> Left, right, node
     */
    int maxValue;
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }
    private int maxPathDown(TreeNode node) {
        if(node == null)
            return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        //we cannot choose both left and right brunches, we select the largest one to prune the lower.
        return Math.max(left, right) + node.val;
    }

}
