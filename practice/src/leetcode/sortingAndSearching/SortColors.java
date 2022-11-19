package leetcode.sortingAndSearching;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/798/
public class SortColors {
/*
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent,
with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
You must solve this problem without using the library's sort function.
 */
    public static void main(String[] args) {
        //2,0,2,1,1,0
        //Output: 0,0,1,1,2,2
    }

    //Same logic with the Dutch Flag
    /** {@link patternsForCodingInterviews.twoPointers.DutchNationalFlagProblem} */

    //Approach 1: One pass, Three pointers
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    //Two pointers
    static void sortColors(int[] nums) {
        //We have 3 pointers in total
        int right = nums.length - 1;
        int left = 0;
        for (int i = 0; i <= right; ) { // '<=' for this edge case: [2, 0, 1]
            if (nums[i] == 2) { // Case of 2, we swap to the end
                swap(nums, i, right);
                right--;
            } else if (nums[i] == 0) { // case 0 we swap with left and i
                swap(nums, i, left);
                i++;
                left++;
            } else { //case if 1, move forward
                i++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
