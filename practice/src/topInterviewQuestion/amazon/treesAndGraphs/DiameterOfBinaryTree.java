package topInterviewQuestion.amazon.treesAndGraphs;
//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2985/
public class DiameterOfBinaryTree {

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        one.left = new TreeNode(2);
        one.left.left = new TreeNode(4);
        one.left.right = new TreeNode(5);
        one.right = new TreeNode(3);
        var obj = new DiameterOfBinaryTree();
        System.out.println(obj.diameterOfBinaryTree(one));
    }


    private int diameter;

    /* Approach 1: DFS Approach
    Time: O(N)
    Space: O(N), If tree balanced -> it would be O(log N)
     */
    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        longestPath(root);
        return diameter;
    }
    //PostOrder -> Left, Right Node
    private int longestPath(TreeNode node) {
        if(node == null)
            return 0;

        int leftPath = longestPath(node.left);
        int rightPath = longestPath(node.right);
        //Update the diameter
        ////We're looking for diameter, that why we include left and right at each step.
        diameter = Math.max(diameter, leftPath + rightPath);
        //Return longest path between right or left
        return Math.max(leftPath, rightPath) + 1;
    }

}
