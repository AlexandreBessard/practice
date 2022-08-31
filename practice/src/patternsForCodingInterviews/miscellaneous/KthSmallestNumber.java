package patternsForCodingInterviews.miscellaneous;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744623686_129Unit
public class KthSmallestNumber {

    public static void main(String[] args) {
        int result =
                KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
        System.out.println("Kth smallest number is: " + result);

        // since there are two 5s in the input array, our 3rd and 4th smallest numbers
        // should be a '5'
        result =
                KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
        System.out.println("Kth smallest number is: " + result);

        result = KthSmallestNumber.findKthSmallestNumber(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Kth smallest number is: " + result);
    }


    /*
    Using QuickSort's Randomized partitioning Scheme
     */
    public static int findKthSmallestNumberQuickSortRandomPivot(int[] nums, int k) {
        return findKthSmallestNumberRecRandom(nums, k, 0, nums.length - 1);
    }
    private static int findKthSmallestNumberRecRandom(int[] nums,
                                                      int k,
                                                      int start,
                                                      int end)
    {
        int p = partitionRandom(nums, start, end);
        if(p == k - 1)
            return nums[p];
        if(p > k - 1)
            return findKthSmallestNumberRecRandom(nums, k, start, p - 1);

        return findKthSmallestNumberRecRandom(nums, k, p + 1, end);
    }
    private static int partitionRandom(int[] nums, int low, int high) {
        if(low == high)
            return low;

        Random randomNum = new Random();
        int pivotIndex = low + randomNum.nextInt(high - low);
        swap(nums, pivotIndex, high);
        int pivot = nums[high];

        for(int i = low; i < high; i++) {
            if(nums[i] < pivot) {
                swap(nums, low++, i);
            }
        }
        //put the pivot in its correct place
        swap(nums, low, high);
        return low;
    }


    /*
    Partition Scheme Of QuickSort
    Time: Worst case: O(N²)
    Space: O(N) for recursion stack
     */
    public static int findKthSmallestNumberQuickSort(int[] nums, int k) {
        return findKthSmallestNumberRec(nums, k, 0, nums.length - 1);
    }
    private static int findKthSmallestNumberRec(int[] nums, int k, int start, int end) {
        int p = partition(nums, start, end);
        if (p == k - 1)
            return nums[p];
        if (p > k - 1) // search lower part
            return findKthSmallestNumberRec(nums, k, start, p - 1);
        // search higher part
        return findKthSmallestNumberRec(nums, k, p + 1, end);
    }
    private static int partition(int[] nums, int low, int high) {
        if (low == high)
            return low;
        int pivot = nums[high];
        for (int i = low; i < high; i++) {
            // all elements less than 'pivot' will be before the index 'low'
            if (nums[i] < pivot)
                swap(nums, low++, i);
        }
        // put the pivot in its correct place
        swap(nums, low, high);
        return low;
    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /*
    Using MinHeap
    Time: Building heap with N elements will take O(N)
    Extracting K numbers will take O(K * logN)
    Overall: O(N + K * logN)
    Space: O(N)
     */
    public static int findKthSmallestNumberMinHeap(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                (n1, n2) -> n2 - n1
        );
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


    /*
    Brut force using sorting
    Time: O(N logN) if we are not using in-place algorithm
    Space: O(N)
     */
    public static int findKthSmallestNumberBrutForceSorting(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[k - 1];
    }

    /*
    Brut force
     */
    //Time: O(N * K)
    //Space: O(1)
    public static int findKthSmallestNumber(int[] nums, int k) {
        int previousSmallestNum = Integer.MIN_VALUE;
        int previousSmallestIndex = -1;
        int currentSmallestNum = Integer.MAX_VALUE;
        int currentSmallestIndex = -1;
        for(int i = 0; i < k; i++) {
            for(int j = 0; j < nums.length; j++) {
                if(nums[j] > previousSmallestNum && nums[j] < currentSmallestNum) {
                    //Found the next smallest number
                    currentSmallestNum = nums[j];
                    currentSmallestIndex = j;
                } else if(nums[j] == previousSmallestNum && j > previousSmallestIndex) {
                    // found a number which is equal to the previous smallest number; since numbers
                    // can repeat, we will consider 'nums[j]' only if it has a different index
                    // than previous smallest
                    currentSmallestNum = nums[j];
                    currentSmallestIndex = j;
                    break; // break here as we have found our definitive next smallest number
                }
            }
            // current smallest number becomes previous smallest number for the next iteration
            previousSmallestNum = currentSmallestNum;
            previousSmallestIndex = currentSmallestIndex;
            currentSmallestNum = Integer.MAX_VALUE;
        }
        return previousSmallestNum;
    }


}
