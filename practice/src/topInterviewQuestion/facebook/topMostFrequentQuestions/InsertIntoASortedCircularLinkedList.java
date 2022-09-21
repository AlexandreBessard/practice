package topInterviewQuestion.facebook.topMostFrequentQuestions;

import topInterviewQuestion.medium.treesAndGraphs.Node;

//https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
public class InsertIntoASortedCircularLinkedList {

    public static void main(String[] args) {
        Node three = new Node(3);
        Node four = new Node(4);
        Node one = new Node(1);
        three.next = four;
        four.next = one;
        one.next = three;
        Node res = insert(three, 2);
        Node curr = res;
        while (curr != null) {
            System.out.print(curr.val + ", ");
            curr = curr.next;
        }
    }

    //Approach 1: two pointers iteration
    /*
    Time: O(n)
    Space: O(1)
     */
    static Node insert(Node head, int insertVal) {
        if (head == null) {
            Node newNode = new Node(insertVal, null);
            newNode.next = newNode;
            return newNode;
        }
        Node prev = head;
        Node curr = head.next;
        boolean toInsert = false;
        do {
            if (prev.val <= insertVal && insertVal <= curr.val) { //InsertVal can be placed, it is between pev and curr range
                toInsert = true;
            } else if (prev.val > curr.val) { //Means we at the end of the linkedList ( it is an increasing order)
                if (insertVal >= prev.val || insertVal <= curr.val) { //See screenshot 'Case 2' from correction for a better understanding
                    toInsert = true;
                }
            }
            if (toInsert) {
                prev.next = new Node(insertVal, curr);
                return head;
            }
            prev = curr;
            curr = curr.next;
        } while (prev != head);
        //Case 3:
        //We have entire looped though the linkedList
        prev.next = new Node(insertVal, curr);
        return head;
    }


    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node node) {
            this.val = val;
            next = node;
        }
    }

}
