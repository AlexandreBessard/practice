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
        //TreeNode res = obj.lowestCommonAncestor(three, five, four);
        TreeNode res = obj.lowestCommonAncestorIterativeApproach(three, five, four);
        System.out.println(res.val);
    }


    //Approach 2: Recursive approach
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/65226/My-Java-Solution-which-is-easy-to-understand
    /*
    Time: O(N)
    Space: O(N)
     */
    //Post Order -> left, right node
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || root == null)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) //If left side is null, return right
            return right;
        else if (right == null) //If right side is null return left side
            return left;
        else
            return root; //Both sides are not null so return this node

    }

    //Approach 2: Iterative using parent pointers
    /*
    Time: O(N)
    Space: O(N)
     */
    public TreeNode lowestCommonAncestorIterativeApproach(TreeNode root, TreeNode p, TreeNode q) {
        // Stack for tree traversal
        Stack<TreeNode> stack = new Stack<>();
        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);
        stack.push(root);
        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) { // (false || true) --> true
            System.out.println(!parent.containsKey(p) + " || " + !parent.containsKey(q));
            System.out.println("result -> " + (!parent.containsKey(p) || !parent.containsKey(q)));
            TreeNode node = stack.pop();
            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }
        System.out.println("final result ->  " + (!parent.containsKey(p) || !parent.containsKey(q)));
        // Ancestors set() for node p.
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor.
        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }

}
