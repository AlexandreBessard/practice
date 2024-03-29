package leetcode.linkedList;

import leetcode.linkedList.ListNode;
import leetcode.linkedList.helper.Helper;

import java.util.ArrayList;
import java.util.List;

public class PalindromeLinkedList {

    public static void main(String[] args) {
        var one = Helper.generateLinkedList(1, 2, 2, 1);

        System.out.println(isPalindromeReverseSecondHalf(one));
        ListNode head = one;
        while (head != null) {
            System.out.print(head.val + ", ");
            head = head.next;
        }
    }

    // Grokking the coding interview
    /** {@link patternsForCodingInterviews.fastAndSlowPointers._1PalindromeLinkedList} */

    //Approach 3: Reverse Second Half In-place
    /*
    Time: O(n)
    Space: O(1)
     */
    static boolean isPalindromeReverseSecondHalf(ListNode head) {
        if (head == null) return true;
        //Find end of first half and reserve second half
        ListNode firstHalfEnd = endFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);
        //Check weather or not there is a palindrome
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        //Restore the list and return the result, Keep in mind, firstHalfEnd is just one node in the middle of the linkedList (pointer)
        firstHalfEnd = reverseList(secondHalfStart);
        return result;
    }

    private static ListNode reverseList(ListNode head) {
        ListNode previous = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    private static ListNode endFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow; //Get the middle of that linkedList
    }

    //Approach 1 Copy into Array List and then Use Two Pointer Technique
    /*
    Time complexity: O(n)
    Space complexity: O(n)
     */
    static boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<>();
        ListNode currentNode = head;
        //Convert LinkeList to Array;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }
        //Use 2 pointer technique
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back)))
                return false;
            front++;
            back--;
        }
        return true;
    }

}
