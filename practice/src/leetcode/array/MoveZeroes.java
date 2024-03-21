package leetcode.array;

import java.util.Arrays;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/567/
//Solution: https://leetcode.com/problems/move-zeroes/discuss/172432/THE-EASIEST-but-UNUSUAL-snowball-JAVA-solution-BEATS-100-(O(n))-%2B-clear-explanation
public class MoveZeroes {
    /*
    Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
    Note that you must do this in-place without making a copy of the array.
     */
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[] {0, 1, 0, 3, 12};
        moveZeroesTwoPointers(nums);
        System.out.println(Arrays.toString(nums));
    }

    /*
    Two pointers
    Time: O(n)
    Space: O(1)
     */
    static void moveZeroesTwoPointers(int[] nums) {
        // Pointer used to know where a zero is located
        int left = 0;
        int current = 0;
        while (current < nums.length) {
            if (nums[current] != 0) {
                // Swap non zero to the left side
                int temp = nums[left];
                nums[left] = nums[current];
                nums[current] = temp;
                // move pointer to the next position
                left++;
            }
            // It is a 0, move to the next position
            current++;
        }
    }

    //Image: practice/resources/moveZeroes.png
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    static void moveZeroes(int[] nums) {
        int snowBallSize = 0; //Count the number of zeros
        for (int i = 0; i < nums.length; i++) { //Loop through each element
            if (nums[i] == 0) {
                snowBallSize++;
            } else if (snowBallSize > 0) {
                int t = nums[i]; //Get the current element
                nums[i] = 0; //Replaced by 0
                nums[i - snowBallSize] = t;
            }
        }
    }

}
