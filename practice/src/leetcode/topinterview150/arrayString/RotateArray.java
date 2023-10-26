package leetcode.topinterview150.arrayString;

import java.util.Arrays;

public class RotateArray {

    // https://leetcode.com/problems/rotate-array/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3); // Output: [5,6,7,1,2,3,4]
        System.out.println(Arrays.toString(nums));
        nums = new int[]{1, 2, 3, 4, 5};
        rotate(nums, -3);
        System.out.println(Arrays.toString(nums));
    }


    /*
    Solution: https://leetcode.com/problems/rotate-array/solutions/1730142/java-c-python-a-very-very-well-detailed-explanation/?envType=study-plan-v2&envId=top-interview-150
    Time: O(n)
    Space: O(1)
     */
    static void rotate(int[] nums, int k) {
        // pointer where is located the latest element to be rotated
        k = k % nums.length;
        if (k < 0) {
            k += nums.length;
        }
        // reverse left side, does not include the k element
        // represent the index
        reverse(nums, 0, nums.length - k - 1);
        // reverse right side
        // does include the k element
        reverse(nums, nums.length - k, nums.length - 1);
        // reverse the entire array
        reverse(nums, 0, nums.length - 1);
    }

    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }


}
