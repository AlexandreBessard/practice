package leetcode.linkedList;

import leetcode.linkedList.helper.Helper;
//https://leetcode.com/problems/swap-nodes-in-pairs/
public class SwapNodesInPairs {
/*
Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 */
    public static void main(String[] args) {
        ListNode n = Helper.generateLinkedList(1, 2, 3, 4);
        n = swapPairsRecursive(n);

        Helper.printLinkedList(n);

        n = Helper.generateLinkedList(1, 2, 3, 4);
        ListNode n2 = swapPairsIterative(n);

        Helper.printLinkedList(n2);
    }

    //Approach 2: Iterative approach
    static ListNode swapPairsIterative(ListNode head) {
        // Dummy node acts as the prevNode for the head node
        // of the list and hence stores pointer to the head node.
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prevNode = dummy;
        while(head != null && head.next != null) {
            //Nodes to be swapped
            var firstNode = head;
            var secondNode = head.next;
            //Swapping
            prevNode.next = secondNode; //Put the second Node first
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;
            // Reinitializing the head and prevNode for next swap
            prevNode = firstNode;
            head = firstNode.next; //Do the jump
        }
        return dummy.next;
    }

    //Approach 1: Recursive
    /*
    Time: O(n) where N is the size of the linkedList
    Space: O(N) stack space utilized for recursion
     */
    static ListNode swapPairsRecursive(ListNode head) {
        // If the list has no node or has only one node left.
        if ((head == null) || (head.next == null)) {
            return head;
        }
        // Nodes to be swapped
        ListNode firstNode = head;
        ListNode secondNode = head.next;
        // Swapping
        firstNode.next  = swapPairsRecursive(secondNode.next);
        secondNode.next = firstNode;
        // Now the head is the second node
        return secondNode;
    }
}
