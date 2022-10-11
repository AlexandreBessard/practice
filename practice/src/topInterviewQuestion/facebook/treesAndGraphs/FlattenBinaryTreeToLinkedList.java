package topInterviewQuestion.facebook.treesAndGraphs;
//https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/322/
public class FlattenBinaryTreeToLinkedList {

    //pre-order traversal:
    //Node, Left, Right
    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        one.left = new TreeNode(2);
        one.left.left = new TreeNode(3);
        one.left.right = new TreeNode(4);
        one.right = new TreeNode(5);
        one.right.right = new TreeNode(6);

        var obj = new FlattenBinaryTreeToLinkedList();
        obj.flattenIterative(one);
    }

    //Approach 3: O(1) Iterative solution
    /*
    Time: O(N)
    Space: O(1)
     */
    public void flattenIterative(TreeNode root) {
        if(root == null) {
            return;
        }
        TreeNode node = root;
        while(node != null) {
            //If node has a left child
            if(node.left != null) { //if null, move to the next node
                //Find the rightmost node
                TreeNode rightmost = node.left;
                while(rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                //Rewire the connections
                //Once we find this rightmost node, we rewire the connections as explained in the intuition section.
                rightmost.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            //move on to the right side of the tree
            node = node.right;
        }
    }

    //Recursion
    private TreeNode flattenTree(TreeNode node) {
        // Handle the null scenario
        if (node == null) {
            return null;
        }
        // For a leaf node, we simply return the
        // node as is.
        if (node.left == null && node.right == null) {
            return node;
        }
        //Recursively flatten the left subtree
        TreeNode leftTail = this.flattenTree(node.left);
        // Recursively flatten the right subtree
        TreeNode rightTail = this.flattenTree(node.right);
        // If there was a left subtree, we shuffle the connections
        // around so that there is nothing on the left side
        // anymore.
        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }
        // We need to return the "rightmost" node after we are
        // done wiring the new connections.
        return rightTail == null ? leftTail : rightTail;
    }
    public void flatten(TreeNode root) {
        this.flattenTree(root);
    }
}

