package topInterviewQuestion.easy.linkedList;

import topInterviewQuestion.leetcode.linkedList.ListNode;

public class ReverseLinkedList {

    public static void main(String[] args) {
        var one = new ListNode(1);
        one.next = new ListNode(2);
        one.next.next = new ListNode(3);
        one.next.next.next = new ListNode(4);
        one.next.next.next.next = new ListNode(5);
        ListNode res = reverseListRecursive(one);
        while(res != null) {
            System.out.print(res.val + ", ");
            res = res.next;
        }
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

    /*
    Iterative
    Time complexity: O(N), N is length of LinkedList
    Space complexity: O(1)
     */
    static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }
}
