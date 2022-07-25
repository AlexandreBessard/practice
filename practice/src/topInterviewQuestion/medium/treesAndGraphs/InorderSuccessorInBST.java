package topInterviewQuestion.medium.treesAndGraphs;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/791/
public class InorderSuccessorInBST {


    public static void main(String[] args) {

    }


    //Approach 2: Using BST properties
    /*
    Time complexity: O(n) for unbalanced tree.
    O(log N) for balanced tree.
    Space complexity: O(1)
     */
    static TreeNode inorderSuccessorBST(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while(root != null) {
            if(p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }




        static TreeNode previous;
    static TreeNode inorderSuccessorNode;
    //Approach 1: Without using BST properties
    /*
    Time complexity: O(N)
    Space complexity: O(N) for the second case.
     */
    static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //Need to find the leftmost node
        if(p.right != null) {
           TreeNode leftmost = p.right;
           while(leftmost.left != null) {
               leftmost = leftmost.left;
           }
           inorderSuccessorNode = leftmost;
        } else {
            // we need to perform the standard inorder traversal and keep track of the previous node
            inorderCase2(root, p);
        }
        return inorderSuccessorNode;
    }
    private static void inorderCase2(TreeNode node, TreeNode p) {
        if(node == null)
            return;
        //Recurse on the left side
        inorderCase2(node.left, p);
        //Check if previous inorder predecessor of node
        if(previous == p && inorderSuccessorNode == null) {
            inorderSuccessorNode = node;
            return;
        }
        //Keeping previous up-to-date for further recursions
        previous = node;
        inorderCase2(node.right, p);
    }

}
