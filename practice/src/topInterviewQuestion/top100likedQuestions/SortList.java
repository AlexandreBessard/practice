package topInterviewQuestion.top100likedQuestions;


import patternsForCodingInterviews.fastAndSlowPointers.ListNode;

//https://leetcode.com/problems/sort-list/
public class SortList {

    public static void main(String[] args) {
        ListNode four1 = new ListNode(4);
        four1.next = new ListNode(2);
        four1.next.next = new ListNode(1);
        four1.next.next.next = new ListNode(3);
        ListNode curr = sortListDivideAndConquer(four1);
        while (curr != null) {
            System.out.print(curr.value + ", ");
            curr = curr.next;
        }
    }

    //Approach 1: Top Down Merge Sort -> Divide and Conquer Strategy
    /*
    Time: O(n log n) -> Split & Merge
    Space: O(log n)
     */
    static ListNode sortListDivideAndConquer(ListNode head) {
        if (head == null || head.next == null) { //Base case
            return head;
        }
        ListNode mid = findMiddle(head); //Get the middle -> Divide
        ListNode left = sortListDivideAndConquer(head); //Conquer left side
        ListNode right = sortListDivideAndConquer(mid); //Conquer right side
        return merge(left, right);
    }

    private static ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.value < list2.value) { //If value less than, come before
                tail.next = list1;
                list1 = list1.next;
                tail = tail.next;
            } else { //(list1.value > list2.value)
                tail.next = list2;
                list2 = list2.next;
                tail = tail.next;
            }
            tail.next = (list1 != null) ? list1 : list2;
        }
        return dummyHead.next;
    }

    private static ListNode findMiddle(ListNode head) {
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            prev = slow; //Need node before current slow because slow will become the middle
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow; //The middle found
        prev.next = null; //Required to execute our base case, set the previous from 'slow' to null
        return mid;
    }

}
