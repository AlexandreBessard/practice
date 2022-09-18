package patternsForCodingInterviews.topKElements;

import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744282812_94Unit
public class KthSmallestNumber {

    public static void main(String[] args) {
        int result =
                KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 3);
        System.out.println("Kth smallest number is: " + result);

        // since there are two 5s in the input array, our 3rd and 4th smallest numbers
        // should be a '5'
        result =
                KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
        System.out.println("Kth smallest number is: " + result);

        result =
                KthSmallestNumber.findKthSmallestNumber(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Kth smallest number is: " + result);
    }

    /*
    Time: O(N * logK)
    Space: O(k) to store 'K' smallest numbers in the heap.
     */
    public static int findKthSmallestNumber(int[] nums, int k) {
        /*
        As we know, the root is the biggest element in the max heap.
        So, since we want to keep track of the ‘K’ smallest numbers,
        we can compare every number with the root while iterating through all numbers,
        and if it is smaller than the root, we’ll take the root out and insert the smaller number
         */
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((n1, n2) -> n2 - n1);
        // put first k numbers in the max heap
        for(int i = 0; i < k; i++) {
            maxHeap.add(nums[i]);
        }
        // go through the remaining numbers of the array, if the number from the array is
        // smaller than the top (biggest) number of the heap, remove the top number from
        // heap and add the number from array
        for(int i = k; i < nums.length; i++) {
            if(nums[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(nums[i]);
            }
        }
        // the root of the heap has the Kth smallest number
        return maxHeap.peek();
    }

}