package patternsForCodingInterviews.treeDFS;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743963987_59Unit
public class TreeDiameter {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println("Tree Diameter: " + findDiameter(root));
        root.left.left = null;
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.left.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(11);
        System.out.println("Tree Diameter: " + findDiameter(root));
    }

    private static int treeDiameter = 0;
    /*
    Time complexity: O(N), traverse each node
    Space complexity: O(N) recursion stack
     */
    public static int findDiameter(TreeNode root) {
        calculateHeight(root);
        return treeDiameter;
    }
    private static int calculateHeight(TreeNode currentNode) {
        if(currentNode == null)
            return 0;
        int leftTreeHeight = calculateHeight(currentNode.left);
        int rightTreeHeight = calculateHeight(currentNode.right);
        //If the current node does not have a left and right subtree, we can not have a path passing through it.
        if(leftTreeHeight != 0 && rightTreeHeight != 0) {
            //Diameter at the current node
            int diameter = leftTreeHeight + rightTreeHeight + 1;
            //Update the global tree
            treeDiameter = Math.max(treeDiameter, diameter);
        }
        return Math.max(leftTreeHeight, rightTreeHeight) + 1;
    }


}
