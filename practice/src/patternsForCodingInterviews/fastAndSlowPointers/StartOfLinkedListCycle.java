package patternsForCodingInterviews.fastAndSlowPointers;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743554403_14Unit
public class StartOfLinkedListCycle {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle start: " +
                findCycleStart(head).value);

        /*
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " +
                findCycleStart(head).value);

        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " +
                findCycleStart(head).value);

         */
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    public static ListNode findCycleStart(ListNode head) {
        int cycleLength = 0;
        //find the LinkedList cycle
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) {
                cycleLength = calculateCycleLength(slow);
                break;
            }
        }
        return findStart(head, cycleLength);
    }
    private static ListNode findStart(ListNode head, int cycleLength) {
        ListNode pointer1 = head;
        ListNode pointer2 = head;
        //move pointer2 ahead 'cycleLength' nodes
        while(cycleLength > 0) {
            pointer2 = pointer2.next;
            cycleLength--;
        }
        //Increment both pointers until they meet at the start of the cycle
        while(pointer1 != pointer2) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        return pointer1;
    }

    private static int calculateCycleLength(ListNode slow) {
        ListNode current = slow;
        int cycleLength = 0;
        do {
            current = current.next;
            cycleLength++;
        } while(current != slow);
        return cycleLength;
    }


}
