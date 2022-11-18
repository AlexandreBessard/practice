package leetcode.treesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/789/
public class PopulatingNextRightPointersInEachNode {
/*
Populate each next pointer to point to its next right node.
If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.
 */
    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        one.left = two;
        one.right = three;
        one.left.left = four;
        one.left.right = five;
        one.right.left = six;
        one.right.right = seven;
        PopulatingNextRightPointersInEachNode.connectBFS(one);
    }

    //Approach 1: Level Order Traversal
    /*
    Time complexity: O(N)
    Space complexity: O(N) BFS: space used by the Queue
     */
    static Node connectBFS(Node root) {
        if(root == null) {
            return new Node();
        }
        Queue<Node> Q = new LinkedList<>();
        Q.add(root);
        while(!Q.isEmpty()) {
            int size = Q.size();
            for(int i = 0; i < size; i++) {
                Node node = Q.poll();
                if(i < size - 1) { //True if we are not at the end of the level
                    node.next = Q.peek();
                }
                if(node.left != null) {
                    Q.add(node.left);
                }
                if(node.right != null) {
                    Q.add(node.right);
                }
            }
        }
        return root;
    }


    //Approach 2: Using previously established next pointers
    /*
    Time complexity: O(n)
    Space complexity: O(1)
     */
    static Node connect(Node root) {
        if(root == null) {
            return root;
        }
        Node leftmost = root;
        while(leftmost.left != null) {
            Node head = leftmost;
            while(head != null) {
                head.left.next = head.right;
                if(head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }
}
