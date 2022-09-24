package patternsForCodingInterviews.treeBFS;

import java.util.LinkedList;
import java.util.Queue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743890075_50Unit
public class ConnectLevelOrderSiblings {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        connect(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();
    }

    public static void connect(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode previous = null; //Reset at the beginning of each level
            int levelSize = queue.size();
            //Connect all nodes for this level
            for (int i = 0; i < levelSize; i++) { //Execute this level
                TreeNode currentNode = queue.poll();
                if (previous != null) { //Previous is null for the first element only (no previous)
                    previous.next = currentNode;
                }
                previous = currentNode;
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
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
            //Note
            /*
            int s;
            int t;
            int i = s = t = 1;
            System.out.println("i : " + i + ", t : " + t + ", s : " + s);
            Output: i : 1, t : 1, s : 1
             */
        }

        //Level order traversal using 'next' pointer
        public void printLevelOrder() {
            TreeNode nextLevelRoot = this;
            while (nextLevelRoot != null) {
                TreeNode current = nextLevelRoot;
                nextLevelRoot = null;
                while (current != null) { //Execute the current level
                    System.out.print(current.val + " ");
                    if (nextLevelRoot == null) { //Get the next level
                        if (current.left != null) {
                            nextLevelRoot = current.left; //Start from the left
                        } else if (current.right != null) {
                            nextLevelRoot = current.right; //Start from the right
                        }
                    }
                    current = current.next;
                }
                System.out.println();
            }
        }
    }
}

