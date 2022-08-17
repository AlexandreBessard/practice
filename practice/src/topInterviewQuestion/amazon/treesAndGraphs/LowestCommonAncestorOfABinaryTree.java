package topInterviewQuestion.amazon.treesAndGraphs;

import java.util.*;

//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2984/
public class LowestCommonAncestorOfABinaryTree {

    public static void main(String[] args) {
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        TreeNode four = new TreeNode(4);
        three.left = five;
        three.left.left = new TreeNode(6);
        three.left.right = new TreeNode(2);
        three.left.right.left = new TreeNode(7);
        three.left.right.right = four;
        three.right = new TreeNode(1);
        three.right.left = new TreeNode(0);
        three.right.right = new TreeNode(8);

        var obj = new LowestCommonAncestorOfABinaryTree();
        TreeNode res = obj.lowestCommonAncestor(three, five, four);
        System.out.println(res.val);
    }


    //Approach 2: Recursive approach
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/65226/My-Java-Solution-which-is-easy-to-understand
    /*
    Time: O(N)
    Space: O(N)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if( root == p || root == q || root == null)
            return root;
        TreeNode left = lowestCommonAncestor( root.left,  p,  q);
        TreeNode right = lowestCommonAncestor( root.right,  p,  q);
        if(left == null)
            return right;
        else if (right == null)
            return left;
        else
            return root;

    }
}
