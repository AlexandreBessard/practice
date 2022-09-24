package patternsForCodingInterviews.treeBFS;

import java.util.LinkedList;
import java.util.Queue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743880609_49Unit
public class LevelOrderSuccessor {

    /*
    Time: O(N)
    Space: O(N)
     */
    public static TreeNode findSuccessor(TreeNode root, int key) {
        if(root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while( ! queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            if(currentNode.left != null) { //Add node on the left first, priority 1
                queue.add(currentNode.left);
            }
            if(currentNode.right != null) { //Add node on the right first, priority 2
                queue.add(currentNode.right);
            }
            if(currentNode.val == key) {
                break;
            }
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        TreeNode result = findSuccessor(root, 3);
        if (result != null)
            System.out.println(result.val + " ");

        root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        result = findSuccessor(root, 9);
        if (result != null)
            System.out.println(result.val + " ");

        result = findSuccessor(root, 12);
        if (result != null)
            System.out.println(result.val + " ");
    }

}
