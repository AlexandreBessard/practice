package topInterviewQuestion.medium.linkedList;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/107/linked-list/784/
public class OddEvenLinkedList {

    public static void main(String[] args) {

    }

    /*
    Time complexity: 0(n), we visit each node once.
    Space complexity: 0(1), all we need is four pointers.
     */
    static ListNode oddEvenList(ListNode head) {
        if(head == null)
            return null;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while(even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }


}
