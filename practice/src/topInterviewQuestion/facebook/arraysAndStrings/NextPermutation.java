package topInterviewQuestion.facebook.arraysAndStrings;

import java.util.Arrays;

//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3012/
public class NextPermutation {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {3, 2, 1};
        int[] nums3 = {1, 1, 5};
        int[] nums4 = {1, 5, 8, 4, 7, 6, 5, 3, 1};
        nextPermutation(nums4);
        int[] nums5 = {0, 1, 2, 5, 3, 3, 0};
        //int[] failCase = {5, 4, 3, 2};
        System.out.println(Arrays.toString(nextPermutationOtherApproach(nums4)));
        //Output: 1, 3, 2
        //System.out.println(Arrays.toString(nums4));
    }

    //Approach with explanation:
    //https://leetcode.com/problems/next-permutation/discuss/13994/Readable-code-without-confusing-ij-and-with-explanation
    /*
    0: Initial sequence
    1: Find longest non-increasing suffix
    2: Identify pivot
    3: Find rightmost successor to pivot in the suffix
    4: Swap with pivot
    5: Reverse the suffix
    6: Done
     */
    /*0*/ public static int[] nextPermutationOtherApproach(int[] nums) {
        // pivot is the element just before the non-increasing (weakly decreasing) suffix
        /*2*/   int pivot = indexOfLastPeak(nums);
        // paritions nums into [prefix pivot suffix]
        if (pivot != -1) {
            //Get first index from the end greater than nums[pivot]
            int nextPrefix = lastIndexOfGreater(nums, nums[pivot]); // in the worst case it's suffix[0]
            // next prefix must exist because pivot < suffix[0], otherwise pivot would be part of suffix
            /*4*/
            swap(nums, pivot, nextPrefix); // this minimizes the change in prefix
        }
        //5
        reverseSuffix(nums, pivot + 1); // reverses the whole list if there was no pivot
        //6 done
        return nums;
        }
    /**
     * Find the last element which is a peak.
     * In case there are multiple equal peaks, return the first of those.
     * @return first index of last peak
     */
    /*1*/ static int indexOfLastPeak(int[] nums) {
        for (int i = nums.length - 1; i > 0; --i) {
            if (nums[i - 1] < nums[i])
                return i - 1;
        }
        return 0;
    }
    /** @return last index where the {@code num > threshold} or -1 if not found */
    /*3*/ static int lastIndexOfGreater(int[] nums, int threshold) {
        for (int i = nums.length - 1; i >= 0; --i) {
            if (threshold < nums[i])
                return i;
        }
        return -1;
    }
    /** Reverse numbers starting from an index till the end. */
    static void reverseSuffix(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    //Approach 2: Single pass approach
    //Brut force
    /*
    Time: O(N)
    Space: O(1)
     */
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while(i >= 0 && nums[i + 1] <= nums[i]) { //true if decreasing order like: 3, 2, 1
            i--;
        }
        if(i >= 0) {
            int j = nums.length - 1;
            while(nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1); //Swap from i + 1 to j (length - 1)

        System.out.println();
    }
    private static void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length - 1;
        while(i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
