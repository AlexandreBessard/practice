package leetcode.linkedList;

import java.util.HashSet;
import java.util.Set;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/107/linked-list/785/
public class IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {
        //4,1,8,4,5
        //5,6,1,8,4,5
        ListNode four = new ListNode(4);
        four.next = new ListNode(1);
        four.next.next = new ListNode(8);
        four.next.next.next = new ListNode(4);
        four.next.next.next.next = new ListNode(5);

        ListNode five = new ListNode(5);
        five.next = new ListNode(6);
        five.next.next = new ListNode(1);
        five.next.next.next = new ListNode(8);
        five.next.next.next.next = new ListNode(4);
        five.next.next.next.next.next = new ListNode(5);

        System.out.println("TEST");
        ListNode result = getIntersectionNodeHashTable(four, five);
        while(result != null) {
            System.out.print(result.val + ", ");
            result = result.next;
        }
    }

    //Approach 3: Two pointers (more difficult to find this solution)
    /*
    Time complexity: O(N + M)
    Space complexity: O(1)
     */
    static ListNode getIntersectionNodeTwoPointers(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;
        // Note: In the case lists do not intersect, the pointers for A and B
        // will still line up in the 2nd iteration, just that here won't be
        // a common node down the list and both will reach their respective ends
        // at the same time. So pA will be NULL in that case.
    }

    //Approach 2: HasTable
    /*
    Time complexity: O(N + M), traverse haeadB and headA, worst case scenario if all nodes in both lists are different.
    Space complexity: O(M) to store nodes from headB in Set
     */
    static ListNode getIntersectionNodeHashTable(ListNode headA, ListNode headB) {
        Set<ListNode> nodesInB = new HashSet<>();
        while(headB != null) { //Put all nodes from B to a set (unique element)
            nodesInB.add(headB);
            headB = headB.next;
        }
        while(headA != null) {
            if(nodesInB.contains(headA)) {
                return headA;
            }
            headA = headA.next;
        }
        return null;
    }

    //Approach 1: Brut force
    /*
    Time complexity: O(N x M)
    Space complexity: O(1)
     */
    static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        while (headA != null) {
            ListNode pB = headB;
            while(pB != null) {
                if(headA == pB) {
                    return headA;
                }
                pB = pB.next;
            }
            headA = headA.next;
        }
        return null;
    }
}
