package topInterviewQuestion.top100likedQuestions;

import patternsForCodingInterviews.fastAndSlowPointers.ListNode;

//https://leetcode.com/problems/linked-list-cycle-ii/
public class LinkedListCycleII {

    public static void main(String[] args) {
        ListNode three = new ListNode(3);
        ListNode two = new ListNode(2);
        three.next = two;
        three.next.next = new ListNode(0);
        three.next.next.next = new ListNode(-4);
        three.next.next.next.next = two;
        System.out.println(detectCycle(three).value);
    }

    static ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an entrance to
        // a cycle.
        ListNode intersect = getIntersect(head);
        if(intersect == null) return null;
        // To find the entrance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while(ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }

    private static ListNode getIntersect(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) { //If no cycle, fast pointer will be null
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return fast;
            }
        }
        return null;
    }


}
