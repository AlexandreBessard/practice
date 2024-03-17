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
        TreeNode res = obj.lowestCommonAncestor(three, five, four);
        System.out.println(res.val);

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(5);

        TreeNode lca = lowestCommonAncestorNonRecursive(root, p, q);
        System.out.println("Lowest Common Ancestor of " + p.val + " and " + q.val + " is: " + lca.val);
    }

    /*
    Time complexity: O(h)
    Space complexity: O(1)
     */
    public static TreeNode lowestCommonAncestorNonRecursive(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            // If both p and q are smaller than the current node, move to the left child
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            }
            // If both p and q are greater than the current node, move to the right child
            else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            }
            // If one node is smaller and the other is greater, then current node is the LCA
            else {
                return root;
            }
        }
        return null; // Return null if root is null
    }

    //Approach: Recursive
    /*
    Time: O(n)
    Space: O(n)
    PostOrder: Left -> Right -> Node
     */
    static TreeNode ans = null;
    static TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        //Traverse the tree
        recurseTree(root, p, q);
        return ans;
    }
    private static int recurseTree(TreeNode currNode, TreeNode p, TreeNode q) {
        //Reach the end of the branch return false
        if(currNode == null) {
            return 0;
        }
        //Left recursion, If left recursion returns true, set left = 1 else 0
        int left = recurseTree(currNode.left, p, q);
        //Right recursion
        int right = recurseTree(currNode.right, p, q);
        // If the current node is one of p or q
        int mid = (currNode == p || currNode == q) ? 1 : 0;
        // If any two of the flags left, right or mid become True
        if(mid + left + right >= 2) {
            ans = currNode;
            return ans.val;
        }
        // Return true if any one of the three bool values is True.
        return (mid + right + left > 0) ? 1 : 0;
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
        Map<TreeNode, TreeNode> parent = new HashMap<>(); //Key: actual node, value: his root node
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
        Set<TreeNode> ancestors = new HashSet<>(); //Contains all parent of that node
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
