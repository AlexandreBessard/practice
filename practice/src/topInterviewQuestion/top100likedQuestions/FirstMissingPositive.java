package topInterviewQuestion.top100likedQuestions;

//https://leetcode.com/problems/first-missing-positive/
public class FirstMissingPositive {
    /*
    Given an unsorted integer array nums, return the smallest missing positive integer.

    You must implement an algorithm that runs in O(n) time and uses constant extra space.
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 0};
        int[] nums2 = {3, 4, -1, 1};
        int[] nums3 = {7, 8, 9, 11, 12};
        System.out.println(firstMissingPositive(nums2));
    }

    //All the questions where we need to find the repeating numbers,
    // or missing numbers can easily be solved with the help of a pattern that is cyclic sort.
    static int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
