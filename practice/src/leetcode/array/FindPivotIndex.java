package leetcode.array;
//https://leetcode.com/problems/find-pivot-index/
public class FindPivotIndex {
/*
Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.

If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
Return the leftmost pivot index. If no such index exists, return -1.
 */
    public static void main(String[] args) {
        int[] nums = {1, 7, 3, 6, 5, 6};
        System.out.println(pivotIndex(nums));
    }

    public static int pivotIndex(int[] nums) {
        int sum = 0;
        int leftSum = 0;
        for(int num : nums) {
            sum += num; //Aggregate all elements
        }
        for(int i = 0; i < nums.length; i++) {
            //Takes the sum and remove the previous elements (leftsum) and the current one (nums[i])
            if(leftSum == sum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i]; //Aggregate all the previous elements
        }
        return -1;
    }



}
