package patternsForCodingInterviews.fastAndSlowPointers;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743582805_17Unit
public class _1PalindromeLinkedList {

    public static void main(String[] args) {
        var head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + isPalindrome(head));

        /*
        head.next.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + isPalindrome(head));

         */
    }

    /*
    Time: O(N)
    Space: O(1)
     */
    public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        //Find middle of the LinkedList
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //slow points to the middle
        ListNode headSecondhalf = reverse(slow); //reverse the second half
        //Store the head of reverse part to revert back later
        // 2 -> 4 -> (6 -> null) <- 4 <- 2
        ListNode copyHeadSecondHalf = headSecondhalf; //Used to know the head
        //compare the first and the second half
        while(head != null && headSecondhalf != null) {
            if(head.value != headSecondhalf.value) {
                break; //No palindrome, exit the while loop
            }
            //Move to the next element for each linkedList
            head = head.next;
            headSecondhalf = headSecondhalf.next;
        }
        reverse(copyHeadSecondHalf); // Revert the reverse of the second half
        if(head == null || headSecondhalf == null) { //If both halves match
            return true;
        }
        return false;
    }
    private static ListNode reverse(ListNode head) {
        ListNode prev = null; //1 pointer
        while(head != null) { //2 pointer
            ListNode next = head.next; //3 pointer
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev; //Head of the reversed linkedList
    }

}
