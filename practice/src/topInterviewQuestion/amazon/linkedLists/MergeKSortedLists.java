package topInterviewQuestion.amazon.linkedLists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/explore/interview/card/amazon/77/linked-list/512/
public class MergeKSortedLists {

    public static void main(String[] args) {
        int[][] nums = {
                {1, 4, 5},
                {1, 3, 4},
                {2, 6}
        };
        ListNode one1 = new ListNode(1);
        one1.next = new ListNode(4);
        one1.next.next = new ListNode(5);

        ListNode one2 = new ListNode(1);
        one2.next = new ListNode(3);
        one2.next.next = new ListNode(4);

        ListNode two = new ListNode(2);
        two.next = new ListNode(6);

        ListNode[] nodes = new ListNode[] {one1, one2, two};
        var obj = new MergeKSortedLists();
        obj.mergeKListsDivideAndConquer(nodes);
        //Output: 1,1,2,3,4,4,5,6
    }

    //Approach 5: Merge with Divide and Conquer
    /*
    Time complexity: O(N log K) where K is the number of linked lists
    N total number of nodes in two lists.
    Space complexity: O(1)
     */
    public ListNode mergeKListsDivideAndConquer(ListNode[] lists) { // lists == [1, 1, 2]
        if(lists.length==0){
            return null;
        }
        int interval = 1;
        while(interval<lists.length){
            //System.out.println(lists.length);
            for (int i = 0; i + interval < lists.length; i=(i+interval*2)) {
                System.out.println("i : " + i + " -> " + (i+interval));
                lists[i]=mergeTwoLists(lists[i],lists[i+interval]);
            }
            interval*=2;
        }
        return lists[0];
    }
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode h = new ListNode(0);
        ListNode ans = h;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                h.next = l1;
                h = h.next;
                l1 = l1.next;
            } else {
                h.next = l2;
                h = h.next;
                l2 = l2.next;
            }
        }
        if(l1 == null) {
            h.next = l2;
        }
        if(l2 == null) {
            h.next = l1;
        }
        return ans.next;
    }
    //Approach 2: Compare one by one
    /*
    Space O(1)
     */
    public ListNode mergeKListsCompareOneByOneSpaceComplexityReduced(ListNode[] lists) { // lists == [1, 1, 2]
        int min_index = 0;
        ListNode head = new ListNode(0);
        ListNode h = head;
        while (true) {
            boolean isBreak = true;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    if (lists[i].val < min) {
                        min_index = i;
                        min = lists[i].val;
                    }
                    isBreak = false;
                }

            }
            if (isBreak) {
                break;
            }
            h.next = lists[min_index];
            h = h.next;
            lists[min_index] = lists[min_index].next;
        }
        h.next = null;
        return head.next;
    }


        //Approach 2: Compare one by one
    /*
    Time complexity: O(kN) where k is the number of linked lists
    N nodes in the final linked list
    Space complexity: O(N) creating a new linked list
     */
        public ListNode mergeKListsCompareOneByOne(ListNode[] lists) { // lists == [1, 1, 2]
            int min_index = 0;
            ListNode head = new ListNode(0);
            ListNode h = head;
            while (true) {
                boolean isBreak = true;
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < lists.length; i++) {
                    if (lists[i] != null) {
                        if (lists[i].val < min) {
                            min_index = i;
                            min = lists[i].val;
                        }
                        isBreak = false;
                    }

                }
                if (isBreak) {
                    break;
                }
                ListNode a = new ListNode(lists[min_index].val);
                h.next = a;
                h = h.next;
                lists[min_index] = lists[min_index].next;
            }
            h.next = null;
            return head.next;
        }

    //Approach 1: Brut Force
    /*
    Time complexity: O(N log N) caused by stable sorting algorithm.
    Space complexity: O(N) caused by sorting algo (merge sort) which requires additional array.
    and Creation of new linkedList
     */
    public ListNode mergeKListsBrutForce(ListNode[] lists) {
        List<Integer> l = new ArrayList<>();
        for(ListNode ln : lists) {
            while(ln != null) {
                l.add(ln.val);
                ln = ln.next;
            }
        }
        Collections.sort(l); //Merge sort
        ListNode head = new ListNode(0);
        ListNode h = head;
        for(int i : l) {
            ListNode t = new ListNode(i);
            h.next = t;
            h = h.next;
        }
        h.next = null;
        return head.next;
    }



}
