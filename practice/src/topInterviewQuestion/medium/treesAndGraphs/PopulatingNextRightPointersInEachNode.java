package topInterviewQuestion.medium.treesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/789/
public class PopulatingNextRightPointersInEachNode {

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



        //Approach 1: Level Order Traversal
    /*
    Time complexity: O(N)
    Space complexity: O(N) BFS: space used by the Queue
     */
    static Node connectBFS(Node root) {
        if(root == null)
            return root;
        Queue<Node> Q = new LinkedList<>();
        Q.add(root);
        while(Q.size() > 0) {
            int size = Q.size();
            for(int i = 0; i < size; i++) {
                Node node = Q.poll();
                if(i < size - 1) {
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

}
