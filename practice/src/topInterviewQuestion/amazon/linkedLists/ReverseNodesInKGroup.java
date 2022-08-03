package topInterviewQuestion.amazon.linkedLists;
//https://leetcode.com/explore/interview/card/amazon/77/linked-list/2977/
public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        one.next = new ListNode(2);
        one.next.next = new ListNode(3);
        one.next.next.next = new ListNode(4);
        one.next.next.next.next = new ListNode(5);
        var obj = new ReverseNodesInKGroup();
        //ListNode curr1 = obj.reverseKGroupRecursive(one, 2);
        ListNode curr = obj.reverseKGroupIterative(one, 2);
        while(curr != null) {
            System.out.print(curr.val + ", ");
            curr = curr.next;
        }
    }

    //Approach 2: Iterative O(1) Space
    /*
    Time complexity: O(N), We process each node exactly twice.
    Once counting the numbers of nodes, and once when reversing the sub-list.
    Space complexity: O(1)
     */
    public ListNode reverseKGroupIterative(ListNode head, int k) {
        ListNode ptr = head;
        ListNode ktail = null;
        //Head of the final modified linked list
        ListNode new_head = null;
        //Keep going until there are nodes in the list
        while(ptr != null) {
            int count = 0;
            //Start counting nodes from the head
            ptr = head;
            //Find the head of the next k nodes
            while(count < k && ptr != null) {
                ptr = ptr.next;
                count++;
            }
            //If we counted k nodes ,reverse them
            if(count == k) {
                //Reverse LinkedList
                ListNode revHead = this.reverseLinkedList(head, k);
                //new_head is head of the final linked list
                if(new_head == null)
                    new_head = revHead;
                //ktail is the tail of the previous block of reversed k nodes
                if(ktail != null)
                    ktail.next = revHead;

                ktail = head;
                head = ptr;
            }
        }
        //Attach the final possibly un-reversed portion
        if(ktail != null)
            ktail.next = head;
        return new_head == null ? head : new_head;
    }





    //Approach 1: Recursion
    /*
    Time complexity: O(N), we process each node exactly twice.
    One for counting the number of nodes and one for reversing.
    Space complexity: O(N/k), the number of recursion calls is determined by both
    k and N.
    Explanation:
    head = {1, 2, 3, 4, 5} k = 2;
    5 <- 3 <- 4 <-1 <- 2
     */
    public ListNode reverseLinkedList(ListNode head, int k) {
        // Reverse k nodes of the given linked list.
        // This function assumes that the list contains
        // atleast k nodes.
        ListNode new_head = null;
        ListNode ptr = head;
        while (k > 0) {
            // Keep track of the next node to process in the
            // original list
            ListNode next_node = ptr.next;
            // Insert the node pointed to by "ptr"
            // at the beginning of the reversed list
            ptr.next = new_head;
            new_head = ptr;
            // Move on to the next node
            ptr = next_node;
            // Decrement the count of nodes to be reversed by 1
            k--;
        }
        // Return the head of the reversed list
        return new_head;
    }

    public ListNode reverseKGroupRecursive(ListNode head, int k) {
        int count = 0;
        ListNode ptr = head;
        // First, see if there are atleast k nodes
        // left in the linked list.
        while (count < k && ptr != null) {
            ptr = ptr.next;
            count++;
        }
        // If we have k nodes, then we reverse them
        if (count == k) {
            // Reverse the first k nodes of the list and
            // get the reversed list's head.
            ListNode reversedHead = this.reverseLinkedList(head, k);
            // Now recurse on the remaining linked list. Since
            // our recursion returns the head of the overall processed
            // list, we use that and the "original" head of the "k" nodes
            // to re-wire the connections.
            head.next = this.reverseKGroupRecursive(ptr, k);
            return reversedHead;
        }
        return head;
    }
}
