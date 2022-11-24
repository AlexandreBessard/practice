package patternsForCodingInterviews.topKElements;

import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744318099_99Unit
public class KthLargestNumberInAStream {

    public static void main(String[] args) {
        int[] input = new int[] { 3, 1, 5, 12, 2, 11 };
        var kthLargestNumber = new KthLargestNumberInAStream(input, 4);
        System.out.println("4th largest number is: " + kthLargestNumber.add(6));
        System.out.println("4th largest number is: " + kthLargestNumber.add(13));
        System.out.println("4th largest number is: " + kthLargestNumber.add(4));
    }

    //Minimum element in a Q are the priority, we keep the largest element
    /*
    Time: O(log K) because we are inserting the new number in the heap
    Space: O(k) for storing numbers in the heap
     */
    PriorityQueue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> n1 - n2);
    final int k;

    public KthLargestNumberInAStream(int[] nums, int k) {
        this.k = k;
        // add the numbers in the min heap
        for(int element : nums) {
            add(element);
        }
    }

    public int add(int num) {
        // add the new number in the min heap
        minHeap.add(num);
        // if heap has more than 'k' numbers, remove one number
        if(minHeap.size() > k) {
            minHeap.poll();
        }
        // return the 'Kth largest number
        return minHeap.peek();
    }

}
