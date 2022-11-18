package leetcode.treesAndGraphs;

import topInterviewQuestion.medium.treesAndGraphs.TreeNode;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/791/
public class InorderSuccessorInBST {


    public static void main(String[] args) {
        topInterviewQuestion.medium.treesAndGraphs.TreeNode five = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(5);
        five.left = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(3);
        five.left.left = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(2);
        five.left.left.left = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(1);
        topInterviewQuestion.medium.treesAndGraphs.TreeNode four = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(4);
        five.left.right = four;
        topInterviewQuestion.medium.treesAndGraphs.TreeNode six = new topInterviewQuestion.medium.treesAndGraphs.TreeNode(6);
        five.right = six;
        //p -> successor of node p is the node with the smallest key greater than p.val
        topInterviewQuestion.medium.treesAndGraphs.TreeNode res = inorderSuccessorBST(five, four);
    }


    //Approach 2: Using BST properties
    /*
    Time complexity: O(n) for unbalanced tree.
    O(log N) for balanced tree.
    Space complexity: O(1)
     */
    static topInterviewQuestion.medium.treesAndGraphs.TreeNode inorderSuccessorBST(topInterviewQuestion.medium.treesAndGraphs.TreeNode root, topInterviewQuestion.medium.treesAndGraphs.TreeNode p) {
        topInterviewQuestion.medium.treesAndGraphs.TreeNode successor = null;
        while (root != null) {
            if (p.val >= root.val) { //If true we go to the right side
                root = root.right;
            } else { //Go to the left side
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }

    static topInterviewQuestion.medium.treesAndGraphs.TreeNode previous;
    static topInterviewQuestion.medium.treesAndGraphs.TreeNode inorderSuccessorNode;

    //Approach 1: Without using BST properties
    /*
    Time complexity: O(N)
    Space complexity: O(N) for the second case.
     */
    static topInterviewQuestion.medium.treesAndGraphs.TreeNode inorderSuccessor(topInterviewQuestion.medium.treesAndGraphs.TreeNode root, topInterviewQuestion.medium.treesAndGraphs.TreeNode p) {
        //Need to find the leftmost node
        if (p.right != null) {
            topInterviewQuestion.medium.treesAndGraphs.TreeNode leftmost = p.right;
            while (leftmost.left != null) {
                leftmost = leftmost.left;
            }
            inorderSuccessorNode = leftmost;
        } else {
            // we need to perform the standard inorder traversal and keep track of the previous node
            inorderCase2(root, p);
        }
        return inorderSuccessorNode;
    }

    private static void inorderCase2(topInterviewQuestion.medium.treesAndGraphs.TreeNode node, TreeNode p) {
        if (node == null)
            return;
        //Recurse on the left side
        inorderCase2(node.left, p);
        //Check if previous inorder predecessor of node
        if (previous == p && inorderSuccessorNode == null) {
            inorderSuccessorNode = node;
            return;
        }
        //Keeping previous up-to-date for further recursions
        previous = node;
        inorderCase2(node.right, p);
    }

}
