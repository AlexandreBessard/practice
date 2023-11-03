package leetcode.topinterview150.linkedlist;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int value) {
        val = value;
        next = null;
    }

    static void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            // true if it is the last element from the list
            if (current.next == null) {
                System.out.println(current.val);
            } else { // NOT the last element form the list
                System.out.print(current.val + " -> ");
            }
            current = current.next;
        }
    }
}
