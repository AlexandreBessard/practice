package topInterviewQuestion.topInterviewQuestions.dynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/learn/card/dynamic-programming/631/strategy-for-solving-dp-problems/4097/
public class HouseRobber {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 4, 9};
        //System.out.println(rob(nums));
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(robBottomUp(nums));
    }

    private static Map<Integer, Integer> memo = new HashMap<>();
    private static int[] nums;

    public static int rob(int[] nums) {
        HouseRobber.nums = nums;
        return dp(nums.length - 1);
    }

    private static int dp(int i) {  //i is our state, represent index of house
        //Base cases
        if (i == 0) { //We only have 1 remaining house, no choice to take this house
            return nums[i];
        }
        if (i == 1) { //Choose between 2 houses
            return Math.max(nums[0], nums[1]);
        }
        //Get from the cache
        if (memo.containsKey(i)) {
            return memo.get(i);
        }
        //Recurrence
        memo.put(i, Math.max(
                dp(i - 1), //We do not take the current one
                dp(i - 2) + nums[i])); //We take the current one and non-adjacent one
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

    //Approach 1: Recursion with Memoization
    /*
    Time complexity: O(N) process at most N recursives calls and cache
    with O(1) computation. Overall -> O(N)
    Space complexity: O(N) caused by the depth of the recursion stack
     */
    private static int[] cache;

    static int robWthRecursionAndMemo(int[] nums) {
        cache = new int[100];
        Arrays.fill(cache, -1);
        return robFrom(0, nums);
    }
    private static int robFrom(int i, int[] nums) {
        //Base case
        if(i >= nums.length) {
            return 0;
        }
        //Get from the cache
        if(cache[i] > -1) {
            return cache[i];
        }
        //Recursion
        int ans = Math.max(
                robFrom(i + 1, nums),
                robFrom(i + 2, nums) + nums[i]);
        //Store in the cache
        cache[i] = ans;
        System.out.println(ans);
        //Return from the cache
        return cache[i];
    }

}

