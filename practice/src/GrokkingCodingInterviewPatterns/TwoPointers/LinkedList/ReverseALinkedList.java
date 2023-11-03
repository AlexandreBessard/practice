package GrokkingCodingInterviewPatterns.TwoPointers.LinkedList;

public class ReverseALinkedList {
    /*
    Given the head of a Singly LinkedList, reverse the LinkedList.
    Write a function to return the new head of the reversed LinkedList
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        ListNode.printLinkedList(head);
        System.out.println();
        ListNode result = reverse(head);
        ListNode.printLinkedList(result);

    }

    /*
    Time: O(n)
    Space: O(1)
     */
    static ListNode reverse(ListNode head) {
        // Check if the list contains at minimum 2 elements
        if (head.next == null) {
            return head;
        }

        ListNode currentNode = head;
        ListNode previousNode = null;
        ListNode nextNode;

        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }

        // Return the new head of the reversed linked list
        return previousNode;
    }

}
