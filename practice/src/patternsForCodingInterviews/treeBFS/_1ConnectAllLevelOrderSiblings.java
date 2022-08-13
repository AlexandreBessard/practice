package patternsForCodingInterviews.treeBFS;

import java.util.LinkedList;
import java.util.Queue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743897497_51Unit
public class _1ConnectAllLevelOrderSiblings {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        connect(root);
        root.printTree();
    }

    public static void connect(TreeNode root) {
        if(root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode currentNode = null;
        TreeNode previousNode = null;
        while( ! queue.isEmpty()) {
            currentNode = queue.poll();
            if(previousNode != null){
                previousNode.next = currentNode;
            }
            previousNode = currentNode;
            if(currentNode.left != null) {
                queue.offer(currentNode.left);
            }
            if(currentNode.right != null) {
                queue.offer(currentNode.right);
            }
        }
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;
        TreeNode(int x) {
            val = x;
            left = right = next = null;
        }
        public void printTree() {
            TreeNode current = this;
            while(current != null) {
                System.out.print(current.val + " ");
                current = current.next;
            }
        }
    }

}
