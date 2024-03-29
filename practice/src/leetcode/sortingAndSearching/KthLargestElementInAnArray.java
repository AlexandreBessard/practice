package leetcode.sortingAndSearching;

import java.util.*;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/800/
public class KthLargestElementInAnArray {
/*
Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

You must solve it in O(n) time complexity.
 */
    public static void main(String[] args) {
        //This approach is a slight modification of the previous approach.
        // If the array is almost/fully sorted and if we pick the rightmost
        // element as a pivot, the partition of left and right subarrays
        // will be highly uneven.
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(findKthLargest(nums, k));
        System.out.println(findKthLargestElementUsingHeap(nums, k));
    }

    //For other approach with heap see below:
    /** {@link patternsForCodingInterviews.topKElements.KthLargestNumberInAStream} */
    /*
    Time: O(N * log K)
    Space: O(K) because we store 'K' smallest numbers in the heap
     */
    public static int findKthLargestElementUsingHeap(int[] nums, int k) {
        //Smallest element are the priority
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        for(int num : nums) {
            minHeap.add(num);
            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return (minHeap.size() > 0) ? minHeap.peek() : -1;
    }

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
    //k_largest represent the element where once sorted, the largest kth element is located
    private static int quickselect(int left, int right, int k_largest) {
        if(left == right)
            return nums[left];
        //Get random pivot from that range (left (included) - right (excluded))
        int pivot_index = left + random.nextInt(right - left); //From pivot left side, it is in ascending order
        pivot_index = partition(left, right, pivot_index);
        if(k_largest == pivot_index) { //
            return nums[pivot_index];
        } else if(k_largest < pivot_index) { //because from pivot left side it is sorted so get a smaller range to get the Kth element
            return quickselect(left, pivot_index - 1, k_largest);
        } else { //Need bigger element
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
            if(nums[i] < pivot) { //Move smaller element to the left
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
