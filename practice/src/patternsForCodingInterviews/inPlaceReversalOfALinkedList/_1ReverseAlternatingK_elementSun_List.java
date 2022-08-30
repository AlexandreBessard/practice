package patternsForCodingInterviews.inPlaceReversalOfALinkedList;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743804225_41Unit
public class _1ReverseAlternatingK_elementSun_List {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        ListNode result = reverse(head, 2);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    public static ListNode reverse(ListNode head, int k) {
        if (k <= 1 || head == null) {
            return head;
        }
        ListNode current = head;
        ListNode previous = null;
        while (current != null) { //Break if we reach the end of the list
            ListNode lastNodeOfPreviousPart = previous;
            //after reversing the list 'current' will become the last node of the sub-list
            ListNode lastNodeOfSubList = current;
            ListNode next = null; //temporarily store the next node
            //reverse 'k' nodes
            for (int i = 0; current != null && i < k; i++) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            //connect with the previous part
            if (lastNodeOfPreviousPart != null) {
                lastNodeOfPreviousPart.next = previous;
            } else { //we are changing the first node (head) of the linkedList
                head = previous;
            }
            //connect with the next part
            lastNodeOfSubList.next = current;
            //skip 'k' nodes
            for (int i = 0; current != null && i < k; i++) {
                previous = current;
                current = current.next;
            }
        }
        return head;
    }


}
