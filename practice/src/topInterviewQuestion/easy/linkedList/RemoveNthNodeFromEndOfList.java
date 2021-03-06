package topInterviewQuestion.easy.linkedList;

public class RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        var one = new ListNode(1);
        one.next = new ListNode(2);
        one.next.next = new ListNode(3);
        one.next.next.next = new ListNode(4);
        one.next.next.next.next = new ListNode(5);
        //ListNode res = removeNthFromEnd(one, 2);
        ListNode res = removeNthFromEndOnePass(one, 2);
        while(res != null) {
            System.out.print(res.val + ", ");
            res = res.next;
        }
    }

    //Approach 1 Two pass algo
    /*
    Time complexity: O(L)
    there are 2L - n
    Space complexity: O(1)
     */
    static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while(first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while(length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }

    /*
    Approach 2: One pass algorithm
    Time complexity: O(L)
    Makes one traversal on the list of L nodes.
    Space complexity: O(1)
     */
    static ListNode removeNthFromEndOnePass(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        //Advances first pointer, gap between first and second are n nodes apart
        for(int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        //Move first to the end maintaining the gap
        while(first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

}
