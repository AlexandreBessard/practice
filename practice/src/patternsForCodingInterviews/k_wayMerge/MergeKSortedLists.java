package patternsForCodingInterviews.k_wayMerge;

import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744411540_108Unit
public class MergeKSortedLists {

    public static void main(String[] args) {
        ListeNode l1 = new ListeNode(2);
        l1.next = new ListeNode(6);
        l1.next.next = new ListeNode(8);

        ListeNode l2 = new ListeNode(3);
        l2.next = new ListeNode(6);
        l2.next.next = new ListeNode(7);

        ListeNode l3 = new ListeNode(1);
        l3.next = new ListeNode(3);
        l3.next.next = new ListeNode(4);

        ListeNode result = merge(new ListeNode[]{l1, l2, l3});
        System.out.print("Here are the elements form the merged list: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }

    /*
    Time: 0(N * logK) we remove and add one element to the heap in each step
    N: total number of elements, K input lists
    Space: O(K) store one number from all K input lists
     */
    public static ListeNode merge(ListeNode[] lists) {
        //get the smaller value in priority
        PriorityQueue<ListeNode> minheap = new PriorityQueue<ListeNode>(
                (n1, n2) -> n1.value - n2.value
        );
        // put the root of each list in the min heap
        for (ListeNode root : lists) {
            minheap.add(root);
        }
        // take the smallest (top) element form the min-heap and add it to the result;
        // if the top element has a next element add it to the heap
        ListeNode resultHead = null;
        ListeNode resultTail = null;
        while (!minheap.isEmpty()) {
            ListeNode node = minheap.poll();
            if (resultHead == null) {
                resultHead = resultTail = node;
            } else {
                resultTail.next = node;
                resultTail = resultTail.next;
            }
            if (node.next != null) {
                minheap.add(node.next);
            }
        }
        return resultHead;
    }


}
