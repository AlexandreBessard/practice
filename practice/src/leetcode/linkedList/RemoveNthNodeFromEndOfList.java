package leetcode.linkedList;

import leetcode.linkedList.helper.Helper;

public class RemoveNthNodeFromEndOfList {

    /*
    Given the head of a linked list, remove the nth node from the end of the list and return its head.
     */

    public static void main(String[] args) {
        //ListNode res = removeNthFromEnd(one, 2);
        ListNode res = Helper.generateLinkedList(1, 2, 3, 4, 5);
        Helper.printLinkedList(res);
        System.out.println("\nAfter removing nth node from the end:");
        res = removeNthFromEnd(res, 2);
        Helper.printLinkedList(res);
    }

    //Approach 1 Two pass algo
    /*
    Time complexity: O(L)
    there are 2L - n
    Space complexity: O(1)
     */
    static ListNode removeNthFromEnd(ListNode head, int n) { //Remove nth node from the end 1-indexed based
        ListNode dummy = new ListNode(0);
        dummy.next = head; //dummy allows you to return the head
        int lengthLinkedList = 0;
        ListNode first = head;
        while(first != null) {
            lengthLinkedList++; //count the lengthLinkedList of the linkedList
            first = first.next;
        }
        lengthLinkedList -= n; //5 - 2 == 3
        first = dummy; //We start from dummy (beginning of the list counting the dummy)
        while(lengthLinkedList > 0) {// we start from dummy because we want the previous node where the next node have to be deleted
            lengthLinkedList--;
            first = first.next;
        }
        first.next = first.next.next; //Remove the node
        return dummy.next; //Return the beginning of that list
    }

    /*
    Approach 2: One pass algorithm
    Time complexity: O(L)
    Makes one traversal on the list of L nodes.
    Space complexity: O(1)
     */
    static ListNode removeNthFromEndOnePass(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        //Advances first pointer, gap between first and second are n nodes apart
        for(int i = 0; i <= n; i++) { //We move first idx n times forward
            first = first.next;
        }
        //Move first to the end maintaining the gap
        while(first != null) {
            first = first.next; //Move both pointers forward until first is null
            second = second.next;
        }
        second.next = second.next.next; //second pointer point just before the node which must be deleted
        return dummy.next; //Return the head (original node)
    }


}
