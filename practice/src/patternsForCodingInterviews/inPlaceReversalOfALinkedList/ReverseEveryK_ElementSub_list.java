package patternsForCodingInterviews.inPlaceReversalOfALinkedList;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743796880_40Unit
public class ReverseEveryK_ElementSub_list {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        ListNode result = reverse(head, 3);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public static ListNode reverse(ListNode head, int k) {
        if(k <= 1 || head == null) {
            return head;
        }
        ListNode current = head;
        ListNode previous = null;
        while(true) {
            ListNode lastNodeOfPreviousPart = previous;
            // after reversing the List 'current' will become the last node of the sub-list
            ListNode latNodeOfSubList = current;
            //used to temporarily store the next node
            ListNode next = null;
            //reverse 'k' nodes
            for(int i = 0; current != null && i < k; i++) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            // connect with the previous part
            if(lastNodeOfPreviousPart != null) {
                // 'previous' is now the first node of the sub-list
                lastNodeOfPreviousPart.next = previous;
            } else { // this means we are changing the first node (head) of the LinkedList
                head = previous;
            }
            // connect with the next part
            latNodeOfSubList.next = current;
            if(current == null) { //break, we have reached the end of the LinkedList
                break;
            }
            //prepare for the next sub-list
            previous = latNodeOfSubList;
        }
        return head;
    }

}
