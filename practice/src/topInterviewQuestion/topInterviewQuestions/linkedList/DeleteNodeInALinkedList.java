package topInterviewQuestion.topInterviewQuestions.linkedList;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/553/
public class DeleteNodeInALinkedList {

    public static void main(String[] args) {
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        four.next = five;
        four.next.next = new ListNode(1);
        four.next.next.next = new ListNode(9);

        deleteNode(five);
        ListNode curr = four;
        while(curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
    }

    static void deleteNode(ListNode node) {
        node.val = node.next.val; //Replace the value of the node to be deleted with the next one
        node.next = node.next.next;
    }


}
