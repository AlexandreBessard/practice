package leetcode.topinterview150.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {

    //https://leetcode.com/problems/linked-list-cycle/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode _2 = new ListNode(2);
        head.next = _2;
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = _2;
        System.out.println(hasCycle(head));
        System.out.println(hasCycleHashmap(head));
    }

    /*
    Two Pointers, fast and slow
    Time: O(n)
    Space: O(1), we do not use an extra HashSet
     */
    static boolean hasCycle(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while (fastPointer != null && fastPointer.next != null) {

            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if (slowPointer == fastPointer) {
                return true;
            }
        }
        return false;
    }

    /*
    HashMap
    Time: O(n)
    Space: O(n)
     */
    static boolean hasCycleHashmap(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            // true means we have a duplicate
            if (!set.add(head)) {
                // add() -> true if we do NOT have a duplicate
                // (false, false) == true
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
