package leetcode.treesAndGraphs;

public class ConstructBinaryTree {

    public static TreeNode constructTreeNode(Integer[] values) { // 3,9,20,null,null,15,7
        return  helper(values,0);
    }

    private static TreeNode helper(Integer[] values, int i) {
        TreeNode root = null;
        // Base case for recursion
        if (i < values.length) {
            root = new TreeNode(values[i]);
            // insert left child
            root.left = helper(values, 2 * i + 1);
            // insert right child
            root.right = helper(values, 2 * i + 2);
        }
        return root;
    }

}
