package topInterviewQuestion.easy.linkedList;

public class MergeTwoSortedLists {

    public static void main(String[] args) {

        var one1 = new ListNode(1);
        one1.next = new ListNode(2);
        one1.next.next = new ListNode(4);

        var one2 = new ListNode(1);
        one2.next = new ListNode(3);
        one2.next.next = new ListNode(4);


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

    /*
    Iteration
    O(n + m) because l1 & l2 is incremented on each loop runs
    for a  number of iterations  equal to the sum of the lengths of the 2 lists.
    Space complexity: O(1)
     */
    static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }

}
