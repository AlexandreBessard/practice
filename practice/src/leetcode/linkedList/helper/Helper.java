package leetcode.linkedList.helper;

import leetcode.linkedList.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    //Generate a Linked List based on the parameters
    public static ListNode generateLinkedList(Integer... nums) {
        List<Integer> list = new ArrayList<>(List.of(nums));
        ListNode head = new ListNode(list.get(0));
        ListNode curr = head;
        for(int i = 1; i < list.size(); i++) {
            curr.next = new ListNode(list.get(i));
            curr = curr.next;
        }
        return head;
    }

    public static void printLinkedList(ListNode node) {
        ListNode curr = node;
        while(curr != null) {
            System.out.print(curr.val + ", ");
            curr = curr.next;
        }
        System.out.println();
    }
}
