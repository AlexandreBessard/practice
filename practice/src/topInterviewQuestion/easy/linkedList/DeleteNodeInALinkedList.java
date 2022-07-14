package topInterviewQuestion.easy.linkedList;
//https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/553/
public class DeleteNodeInALinkedList {

    public static void main(String[] args) {

    }

    void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


}
