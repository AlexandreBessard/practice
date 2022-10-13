package topInterviewQuestion.top100likedQuestions;

//https://leetcode.com/problems/maximum-product-subarray/
public class MaximumProductSubarray {

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        System.out.println(maxProductBrutForce(nums));
        System.out.println(maxProductDP(new int[]{2, -5, 3, 1, -4, 0, -10, 2, 8}));
    }

    //Approach 2: Dynamic Programming
    /*
    Handle Zeros & Negative numbers
    Time: O(N)
    Space: O(1)
     */
    static int maxProductDP(int[] nums) {
        if(nums.length == 0) return 0;
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = maxSoFar;
        for(int i = 1; i < nums.length; i++) {
            int curr = nums[i];

            int tempMax = Math.max(curr, Math.max((maxSoFar * curr), (minSoFar * curr)));
            minSoFar = Math.min(curr, Math.min((maxSoFar * curr), (minSoFar * curr)));

            maxSoFar = tempMax;
            result = Math.max(maxSoFar, result);
        }
        return result;
    }

    //Brut force
    /*
    Time: O(N²), we are checking every possible contiguous subarray following every element in nums
    Space: O(1)
     */
    static int maxProductBrutForce(int[] nums) {
        if(nums.length == 0) return 0;
        int result = nums[0];
        for(int i = 0; i < nums.length; i++) {
            int accu = 1;
            for(int j = i; j < nums.length; j++) {
                accu *= nums[j];
                result = Math.max(result, accu);
            }
        }
        return result;
    }
}
