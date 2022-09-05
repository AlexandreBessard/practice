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
        //Output: 1, 3, 2
        System.out.println(Arrays.toString(nums4));
    }

    //Approach 2: Single pass approach
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
