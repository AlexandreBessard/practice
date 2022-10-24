package topInterviewQuestion.medium.linkedList;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/107/linked-list/783/
public class  AddTwoNumbers {

    public static void main(String[] args) {

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
        while(l1 != null || l2 != null || carry != 0) {
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
