package topInterviewQuestion.easy.practices;

import leetcode.linkedList.ListNode;

public class DeleteNodeInALinkedList {

    public static void main(String[] args) {
        var four = new ListNode(4);
        var five = new ListNode(5);
        four.next = five;
        four.next.next = new ListNode(1);
        four.next.next.next = new ListNode(9);
        deleteNode(five);
    }

    static void deleteNode(ListNode node) {
        int val = node.next.val;
        node.val = val;
        node.next = node.next.next;
    }

}
