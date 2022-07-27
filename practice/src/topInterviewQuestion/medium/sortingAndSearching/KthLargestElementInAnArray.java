package topInterviewQuestion.medium.sortingAndSearching;

import java.util.Random;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/800/
public class KthLargestElementInAnArray {

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;

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
    private static int quickselect(int left, int right, int k_smallest) {
        if(left == right)
            return nums[left];
        //Random pivot
        int pivot_index = left + random.nextInt(right - left);
        pivot_index = partition(left, right, pivot_index);
        if(k_smallest == pivot_index) {
            return nums[pivot_index];
        } else if(k_smallest < pivot_index) {
            return quickselect(left, pivot_index - 1, k_smallest);
        } else {
            return quickselect(pivot_index + 1, right, k_smallest);
        }
    }
    private static int partition(int left, int right, int pivot_index) {
        int pivot = nums[pivot_index];
        //move pivot to the end:
        swap(pivot_index, right);
        int store_index = left;
        //Move smaller element to the left
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




}
