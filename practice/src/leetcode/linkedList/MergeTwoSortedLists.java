package leetcode.linkedList;

import leetcode.linkedList.helper.Helper;

public class MergeTwoSortedLists {

    public static void main(String[] args) {
        var one1 = Helper.generateLinkedList(1, 2, 4);
        var one2 = Helper.generateLinkedList(1, 3, 4);
        ListNode curr = mergeTwoLists(one1, one2);;
        while(curr != null) {
            System.out.print(curr.val + ", ");
            curr = curr.next;
        }
    }

    /*
    Iteration
    O(n + m) because l1 & l2 is incremented on each loop runs
    for a  number of iterations  equal to the sum of the lengths of the 2 lists.
    Space complexity: O(1)
     */
    static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1); //Used to return the head of our LinkedList at the end
        ListNode prev = preHead; //prev start from the preHead
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) { //Smaller value comes first
                prev.next = l1;
                l1 = l1.next;
            } else { //Value is greater
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = (l1 == null) ? l2 : l1; //We reached the end of one of the linkedList (both are equal in length)
        return preHead.next;
    }

    /*
    Recursive
    Time complexity: O(n + m) -> n and m represents l1 and l2 respectively
    Space complexity: O(n + m)
     */
    static ListNode mergeTwoSortedListRecursive(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        else if(l1.val < l2.val) {
            l1.next = mergeTwoSortedListRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoSortedListRecursive(l1, l2.next);
            return l2;
        }
    }

}
