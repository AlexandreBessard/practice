package leetcode.linkedList;


//https://leetcode.com/explore/interview/card/top-interview-questions-medium/107/linked-list/783/
public class  AddTwoNumbers {
/*
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself
 */
    public static void main(String[] args) {
        var two = new ListNode(2);
        two.next = new ListNode(4);
        two.next.next = new ListNode(3);

        var five = new ListNode(5);
        five.next = new ListNode(6);
        five.next.next = new ListNode(4);

        ListNode curr = addTwoNumbers(two, five);;
        while(curr != null) {
            System.out.print(curr.val + ", ");
            curr = curr.next;
        }
    }

    //Approach 1: Element Math
    /*
    Time complexity: 0(max(m, n)) m and represents the length of l1 and l2 respectively, Iterates at most max(m, n) times.
    Space complexity: O(max(m, n), the length of the new list
     */
    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0); //Pointer to get our result
        ListNode curr = dummyHead;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0) { //If one of these condition is true
            //Get value from each node
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            //Find sum
            int sum = carry + x + y;
            //Set carry
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }
        }
        return dummyHead.next;
    }
}
