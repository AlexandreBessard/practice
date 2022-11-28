package leetcode.linkedList;

import leetcode.linkedList.helper.Helper;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/107/linked-list/784/
public class OddEvenLinkedList {
/*
Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
The first node is considered odd, and the second node is even, and so on.
Note that the relative order inside both the even and odd groups should remain as it was in the input.
You must solve the problem in O(1) extra space complexity and O(n) time complex
 */
    public static void main(String[] args) {
        var one = Helper.generateLinkedList(1, 2, 3, 4, 5);

        ListNode res = oddEvenList(one);
        while(res != null) {
            System.out.print(res.val + ", ");
            res = res.next;
        }
    }

    /*
    Time complexity: 0(n), we visit each node once.
    Space complexity: 0(1), all we need is four pointers.
     */
    static ListNode oddEvenList(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode odd = head; // (1)
        ListNode even = head.next; // (2)
        ListNode evenHead = even; //variable to know where even linked-list start
        while(even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead; //Link the end of odd linked-list to the head because first node is considered odd and second is even
        return head;
    }


}
