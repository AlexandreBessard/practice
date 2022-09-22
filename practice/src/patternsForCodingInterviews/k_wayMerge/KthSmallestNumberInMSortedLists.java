package patternsForCodingInterviews.k_wayMerge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744417564_109Unit
public class KthSmallestNumberInMSortedLists {

    public static void main(String[] args) {
        Integer[] l1 = new Integer[]{2, 6, 8};
        Integer[] l2 = new Integer[]{3, 6, 7};
        Integer[] l3 = new Integer[]{1, 3, 4};
        LinkedList<Integer[]> lists = new LinkedList<>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int result = findKthSmallest(lists, 5);
        System.out.print("Kth smallest number is: " + result);
    }

    /*
    Time: O(K * log M) k elements among all the arrays, M total number of input arrays.
    Space: O(M) because at any time, our min-heap will be storing one number from all M input arrays.
     */
    public static int findKthSmallest(LinkedList<Integer[]> lists, int k) {
        //Priority use Node object to get our smaller element based on the 'lists'
        //Because it is the smaller element, return that Node
        PriorityQueue<Node> minHeap = new PriorityQueue<>(
                (n1, n2) -> lists.get(n1.arrayIndex)[n1.elementIndex] -
                        lists.get(n2.arrayIndex)[n2.elementIndex]
        );
        // put the 1st element of each array in the min heap
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                //i represents the corresponding array and 0 is the first index of that array
                minHeap.add(new Node(0, i)); //Same Node object as the size of that list
            }
        }
        // take the smallest (top) element form the min heap, if the running count is equal
        // to k return the number if the array of the top element has more elements, add the
        // next element to the heap
        int numberCount = 0;
        int result = 0;
        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            result = lists.get(node.arrayIndex)[node.elementIndex];
            if (++numberCount == k) {
                break;
            }
            node.elementIndex++;
            if (lists.get(node.arrayIndex).length > node.elementIndex) { //False if we are Out-ofBound of the array
                minHeap.add(node);
            }
        }
        return result;
    }

    static class Node { //This class is just used to access our elements from a 'lists of arrays'
        int elementIndex;
        int arrayIndex;

        Node(int elementIndex, int arrayIndex) {
            this.elementIndex = elementIndex;
            this.arrayIndex = arrayIndex;
        }
    }

}
