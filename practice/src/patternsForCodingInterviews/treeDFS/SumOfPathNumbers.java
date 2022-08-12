package patternsForCodingInterviews.treeDFS;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743939828_56Unit
public class SumOfPathNumbers {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
        System.out.println("Total Sum of Path Numbers: " +
                SumOfPathNumbers.findSumOfPathNumbers(root));
    }

    public static int findSumOfPathNumbers(TreeNode root) {
        return findRootToLeafPathNumbers(root, 0);
    }
    /*
    Time complexity: O(N) where N is the total number of nodes in the tree.
    Space complexity: O(N) Space for recursion stack.
     */
    private static int findRootToLeafPathNumbers(TreeNode current, int pathSum) {
        if(current == null)
            return 0;
        pathSum = 10 * pathSum + current.val;
        if(current.left == null && current.right == null)
            return pathSum;
        //Traverse left and right
        return findRootToLeafPathNumbers(current.left, pathSum)
                + findRootToLeafPathNumbers(current.right, pathSum);
    }

}
