package algorithms.recursion;

public class SwapNodesInPairs {

    public static void main(String[] args) {
        ListNode n = swapPairsRecursive(newInstance());
        while(n != null) {
            System.out.print(n.val + ", ");
            n = n.next;
        }
        System.out.println();
        ListNode n2 = swapPairsIterative(newInstance());
        while(n2 != null) {
            System.out.print(n2.val + ",  ");
            n2 = n2.next;
        }
    }

    //Approach 2: Iterative approach
    static ListNode swapPairsIterative(ListNode head) {
        // Dummy node acts as the prevNode for the head node
        // of the list and hence stores pointer to the head node.
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prevNode = dummy;
        while(head != null && head.next != null) {
            //Nodes to be swapped
            var firstNode = head;
            var secondNode = head.next;
            //Swapping
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;
            // Reinitializing the head and prevNode for next swap
            prevNode = firstNode;
            head = firstNode.next; //Do the jump
        }
        return dummy.next;
    }

    //Approach 1: Recursive
    /*
    Time: O(n) where N is the size of the linkedList
    Space: O(N) stack space utilized for recursion
     */
    static ListNode swapPairsRecursive(ListNode head) {
        // If the list has no node or has only one node left.
        if ((head == null) || (head.next == null)) {
            return head;
        }
        // Nodes to be swapped
        ListNode firstNode = head;
        ListNode secondNode = head.next;
        // Swapping
        firstNode.next  = swapPairsRecursive(secondNode.next);
        secondNode.next = firstNode;
        // Now the head is the second node
        return secondNode;
    }

    private static ListNode newInstance() {
        ListNode one = new ListNode(1);
        one.next = new ListNode(2);
        one.next.next = new ListNode(3);
        one.next.next.next = new ListNode(4);
        return one;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
