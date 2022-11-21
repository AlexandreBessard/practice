package leetcode.array;

import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/running-sum-of-1d-array/
public class RunningSumOf1DArray {
/*
Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).

Return the running sum of nums.
 */
    public static void main(String[] args) {
        int[] input = {1,2,3,4};
        for(int num : runningSumWithoutExtraSpace(input)) {
            System.out.print(num + ", ");
        }
        System.out.println("\n");
        for(int num : runningSpaceExtraSpace(new int[]{1, 2, 3, 4})) {
            System.out.print(num + ", ");
        }
    }

    //Approach 2: Using Input Array for Output, without using extra space
    /*
    Time: O(n)
    Space: O(1)
     */
    public static int[] runningSumWithoutExtraSpace(int[] nums) {
        //First element stay as is, start at index 1
        for(int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        return nums;
    }

    //Approach 1: Using extra space
    /*
    Time; O(n)
    Space: O(n)
     */
    public static int[] runningSpaceExtraSpace(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            result[i] = nums[i] + result[i - 1];
        }
        return result;
    }

}
