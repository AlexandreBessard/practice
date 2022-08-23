package patternsForCodingInterviews.topKElements;

import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744351402_102Unit
public class SumOfElements {

    public static void main(String[] args) {
        int result =
                findSumOfElements(new int[] { 1, 3, 12, 5, 15, 11 }, 3, 6);
        System.out.println(
                "Sum of all numbers between k1 and k2 smallest numbers: " + result);

        result = findSumOfElements(new int[] { 3, 5, 8, 7 }, 1, 4);
        System.out.println(
                "Sum of all numbers between k1 and k2 smallest numbers: " + result);
    }

    /*
    Time: O(N logK2) since we only the top K2 numbers in the max-heap at any tie
    Space: O(K2) need to store the smallest K2 numbers in the heap
     */
    public static int findSumOfElements(int[] nums, int k1, int k2) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> n1 - n2);
        //Insert all numbers to the min heap
        for(int i = 0; i < nums.length; i++) {
            minHeap.add(nums[i]);
        }
        // remove k1 small numbers from the min heap
        for(int i = 0; i < k1; i++) {
            minHeap.poll();
        }
        int elementSum = 0;
        // sum next k2-k1-1 numbers
        for(int i = 0; i < k2 - k1 - 1; i++) {
            elementSum += minHeap.poll();
        }
        return elementSum;
    }

}
