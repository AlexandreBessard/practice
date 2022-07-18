package topInterviewQuestion.easy.dynamicProgramming;

import java.util.Arrays;

public class HouseRobber {

    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        int[] nums2 = {2,7,9,3};
        //Output 12
        System.out.println("Result : " + rob(nums));
        System.out.println("Result DP -> " + robDynamicProgramingBottomUp(nums2));
    }

    //Approach 2 : Dynamic Programming (Bottom-up)
    /*

     */
    static int robDynamicProgramingBottomUp(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;
        int[] dp = new int[n + 1];
        dp[0] = 0; //zero house
        dp[1] = nums[0]; //one house
        for(int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }


    private static int[] memo;
    //Approach 1: Recursion with Memoization
    /*
    Time complexity: O(N) process at most N recursives calls and cache
    with O(1) computation. Overall -> O(N)
    Space complexity: O(N) caused by the depth of the recursion stack
     */
    static int rob(int[] nums) {
        memo = new int[100];
        Arrays.fill(memo, -1);
        return robFrom(0, nums);
    }
    private static int robFrom(int i, int[] nums) {
        if(i >= nums.length)
            return 0;
        if(memo[i] > -1)
            return memo[i];
        int ans = Math.max(
                robFrom(i + 1, nums),
                robFrom(i + 2, nums) + nums[i]);
        memo[i] = ans;
        System.out.println(ans);
        return ans;
    }


}
