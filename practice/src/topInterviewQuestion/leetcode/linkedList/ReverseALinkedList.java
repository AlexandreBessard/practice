package topInterviewQuestion.leetcode.linkedList;

public class ReverseALinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);

        ListNode result = reverse(head);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    public static ListNode reverse(ListNode head) {
        //3 pointers
        ListNode current = head;
        ListNode previous = null;
        ListNode next = null;
        while(current != null) {
            next = current.next; //Get the next one
            current.next = previous; //set pointer to the previous one.
            previous = current; // previous become current for our next iteration
            current = next; // move forward
        }
        return previous; //Represent the head since both next and current are null (end of the linkedList)
    }
}
