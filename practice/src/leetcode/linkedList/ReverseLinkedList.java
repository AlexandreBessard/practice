package leetcode.linkedList;

import leetcode.linkedList.helper.Helper;

public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode head = Helper.generateLinkedList(2, 4, 6, 8, 10);
        ListNode result = reverse(head);
        Helper.printLinkedList(result);
    }

    /*
    Iterative
    Time: O(N)
    Space: O(1)
     */
    public static ListNode reverse(ListNode head) {
        //3 pointers
        ListNode current = head;
        ListNode previous = null;
        ListNode next = null;
        while(current != null) {
            next = current.next; //Get the next one
            current.next = previous; //set pointer to the previous one.
            previous = current; // previous become current for our next iteration
            current = next; // move forward
        }
        return previous; //Represent the head since both next and current are null (end of the linkedList)
    }

    /*
    Recursive
    Time complexity: O(n) assume n is the list's length
    Space complexity: O(n) stack recursion could go to n levels deep.
     */
    static ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) { //Base case
            return head; //Return 5 to the caller node 4
        }
        ListNode p = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return p; //Return the header along the way (latest linkedList element)
    }
}
