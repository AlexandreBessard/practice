package patternsForCodingInterviews.topKElements;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744276684_93Unit
public class TopKNumbers {

    public static void main(String[] args) {
        List<Integer> result =
                findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
        System.out.println("Here are the top K numbers: " + result);

        result = findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Here are the top K numbers: " + result);
    }

    /*
    Time: O(N * logK)
     we can find the smallest number in a min-heap in constant time O(1)
     the smallest number is always at the root of the heap
     Extracting the smallest number from a min-heap will take O(logN)
     (if the heap has ‘N’ elements) as the heap needs to readjust after the removal of an element.
     Space: O(K) we need to store K numbers in the heap
     */
    public static List<Integer> findKLargestNumbers(int[] nums, int k) {
        //Smaller element are the priority to be removed from the heap
        PriorityQueue<Integer> minHeap  = new PriorityQueue<>((n1, n2) -> n1 - n2);
        //Put first 'K' numbers in the minHeap
        for(int i = 0; i < k; i++) { //Start from 0 to k
            minHeap.add(nums[i]);
        }
        // go through the remaining numbers of the array, if the number from the array is
        // bigger than the top (smallest) number of the min-heap, remove the top number from
        // heap and add the number from array
        for(int i = k; i < nums.length; i++) { //Start from k to end
            if(nums[i] > minHeap.peek()) { //True if we have found bigger number
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }
        return new ArrayList<>(minHeap);
    }
}
