package algorithms.dynamicProgramming.strategicApproachToDP.Example198;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/learn/card/dynamic-programming/631/strategy-for-solving-dp-problems/4097/
public class HouseRobber {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 4, 9};
        System.out.println(rob(nums));
        System.out.println(robBottomUp(nums));
    }

    private static Map<Integer, Integer> memo = new HashMap<>();
    private static int[] nums;

    public static int rob(int[] nums) {
        HouseRobber.nums = nums;
        return dp(nums.length - 1);
    }

    private static int dp(int i) {  //i is our state
        //Base cases
        if (i == 0) {
            return nums[i];
        }
        if (i == 1) {
            return Math.max(nums[0], nums[1]);
        }
        //Get from the cache
        if (memo.containsKey(i)) {
            return memo.get(i);
        }
        //Recurrence
        memo.put(i, Math.max(
                dp(i - 1),
                dp(i - 2) + nums[i]));
        //Return from the cache
        return memo.get(i);
    }

    //------------- BOTTOM UP
    public static int robBottomUp(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0]; //Rob the first house
        dp[1] = Math.max(nums[0], nums[1]); //Rob the first or second house
        for (int i = 2; i < nums.length; i++) {
            //Recurrence relation
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
}
