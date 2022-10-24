package topInterviewQuestion.medium.sortingAndSearching;

import java.util.PriorityQueue;
import java.util.Random;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/800/
public class KthLargestElementInAnArray {

    public static void main(String[] args) {
        //This approach is a slight modification of the previous approach.
        // If the array is almost/fully sorted and if we pick the rightmost
        // element as a pivot, the partition of left and right subarrays
        // will be highly uneven.
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(findKthLargest(nums, k));
    }

    //For other approach with heap:
    /** {@link patternsForCodingInterviews.topKElements.KthLargestNumberInAStream} */

    private static int[] nums;
    private static int size;
    private final static Random random = new Random();
    //Approach 2: QuickSelect
    /*
    Time complexity: O(N), O(N²) in the worst case scenario
    Space complexity: O(1)
     */
    static int findKthLargest(int[] nums, int k) {
        KthLargestElementInAnArray.nums = nums;
        KthLargestElementInAnArray.size = nums.length;
        //kth largest: N - k
        return quickselect(0, size - 1, size - k);
    }
    //k_largest represent the element where once sorted, the largest kth element will be
    private static int quickselect(int left, int right, int k_largest) {
        if(left == right)
            return nums[left];
        //Random pivot
        int pivot_index = left + random.nextInt(right - left);
        pivot_index = partition(left, right, pivot_index);
        if(k_largest == pivot_index) {
            return nums[pivot_index];
        } else if(k_largest < pivot_index) {
            return quickselect(left, pivot_index - 1, k_largest);
        } else {
            return quickselect(pivot_index + 1, right, k_largest);
        }
    }
    private static int partition(int left, int right, int pivot_index) {
        int pivot = nums[pivot_index];
        //move pivot to the end:
        swap(pivot_index, right);
        int store_index = left;
        //Move smaller element go to the left
        for(int i = left; i <= right; i++) {
            if(nums[i] < pivot) {
                swap(store_index, i);
                store_index++;
            }
        }
        //Move pivot to its final place
        swap(store_index, right);
        return store_index;
    }
    private static void swap(int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }



    //Approach 1: Heap
    /*
    Time: O(N log N) caused by the heap
    Space: O(k) to store the heap elements
     */
    public int findKthLargestHeap(int[] nums, int k) {
        //init heap, the smallest element first (smallest element are the priority)
        PriorityQueue<Integer> heap =
                new PriorityQueue<>((n1, n2) -> n1 - n2);
        //keep k largest element in the heap
        for(int n : nums) {
            heap.add(n);
            if(heap.size() > k) {
                heap.poll(); //Remove the smallest element from the heap
            }
        }
        return heap.poll();
    }




}
