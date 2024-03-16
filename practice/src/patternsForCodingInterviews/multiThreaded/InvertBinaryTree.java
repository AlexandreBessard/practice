package patternsForCodingInterviews.multiThreaded;

import java.util.Stack;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62bcedcfddd2aUnit
public class InvertBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(14);
        root.right.right = new TreeNode(19);
        root.right.right.right = new TreeNode(20);

        InvertBinaryTree.invertTree(root);
        System.out.println(root.right.val == 4);
        System.out.println(root.left.val == 15);
        System.out.println(root.left.right.val == 14);
        System.out.println(root.left.left.val == 19);
        System.out.println(root.left.left.left.val == 20);

        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root1.right = new TreeNode(7);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(9);
        TreeNode res = InvertBinaryTree.invertBinaryTreeNonRecursive(root1);
    }

    /*
    MULTITHREADED

     */
    public static TreeNode invertTree(TreeNode root) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        return invertTreeMultiThreaded(root, numThreads);
    }

    private static TreeNode invertTreeMultiThreaded(TreeNode root, int numThreads) {
        if (root == null) return null;
        // invert the current node
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if (numThreads > 0) {
            // spawn a separate thread to invert the left sub-tree
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    invertTreeMultiThreaded(root.left, numThreads/2);
                }
            });
            t1.start();
            // invert the right sub-tree in the same thread
            invertTreeMultiThreaded(root.right, numThreads/2);

            try {
                t1.join(); // wait for the thread inverting the left subtree
            } catch(InterruptedException e){ System.out.println(e); }

        } else {
            invertTreeMultiThreaded(root.left, 0);
            invertTreeMultiThreaded(root.right, 0);
        }
        return root;
    }

    // Non recursive
    /*
    Time: O(n) where n is the number of nodes in the binary tree
    Space: O(n) In balanced binary tree contains half of the total nodes
     */
    static TreeNode invertBinaryTreeNonRecursive(TreeNode root) {
        if (root == null)
            return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            // Get the current node from the stack
            TreeNode node = stack.pop();
            //Invert children of the current node
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            // If child nodes are not null, push them to the stack
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return root;
    }

    /*
    Recursive
    Time: O(N)
    Space O(H)
     */
    public static TreeNode invertTreeNonConcurrency(TreeNode root) {
        if(root == null)
            return null;
        //invert children of the current node
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        //Invert left and right
        invertTreeNonConcurrency(root.left);
        invertTreeNonConcurrency(root.right);
        return root;
    }

}
