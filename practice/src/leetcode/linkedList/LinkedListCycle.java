package leetcode.linkedList;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/773/
public class LinkedListCycle {

    public static void main(String[] args) {
        var three = new ListNode(3);
        var two = new ListNode(2);
        three.next = two;
        three.next.next = new ListNode(0);
        three.next.next.next = new ListNode(-4);
        three.next.next.next.next = two;
        System.out.println(hasCycleFlyodCycleAlgo(three));
    }

    //Approach 2: Flyod's CycleFinding Algo
    /*
    Time complexity: O(n + k) which is O(n) : n numbers of nodes.
    Space complexity: O(1)
     */
    static boolean hasCycleFlyodCycleAlgo(ListNode head) {
        if(head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                return true; //found a cycle
            }
        }
        return false;
    }

    //Approach 1: HashTable
    /*
    Time complexity: O(n), Visit n elements in the list at most once.
    Adding node to hashTable cost O(1) time.
    Space complexity: O(n) depends on element on the Set
     */
    static boolean hasCycle(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while(head != null) {
            if(nodesSeen.contains(head))
                return true;
            nodesSeen.add(head);
            head = head.next;
        }
        return false;
    }

}
