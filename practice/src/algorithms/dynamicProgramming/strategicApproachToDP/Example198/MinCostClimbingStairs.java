package algorithms.dynamicProgramming.strategicApproachToDP.Example198;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/learn/card/dynamic-programming/631/strategy-for-solving-dp-problems/4040/
public class MinCostClimbingStairs {

    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairsTopDown(cost));
        System.out.println(minCostClimbingStairsBottomUp(cost));
    }

    //Top Down
    static int[] cost;
    static Map<Integer, Integer> memo = new HashMap<>();

    static int minCostClimbingStairsTopDown(int[] cost) {
        MinCostClimbingStairs.cost = cost;
        return minimumCost(cost.length);
    }

    private static int minimumCost(int i) { //the minimum cost to reach the ith step
        //Base case
        //The base cases for this function will be minimumCost(0) = minimumCost(1) = 0,
        // since we are allowed to start on either step 0 or step 1
        if (i <= 1) { //True if i == 0 or i == 1
            return 0;
        }
        //Get from cache
        if (memo.containsKey(i)) {
            return memo.get(i);
        }
        //Recursion
        int downOne = cost[i - 1] + minimumCost(i - 1);
        int downTwo = cost[i - 2] + minimumCost(i - 2);
        memo.put(i, Math.min(downOne, downTwo));
        //Return from the cache
        return memo.get(i);
    }

    //Bottom Up
    /*
    Time: O(N)
    Space: O(N)
     */
    static int minCostClimbingStairsBottomUp(int[] cost) {
        // The array's length should be 1 longer than the length of cost
        // This is because we can treat the "top floor" as a step to reach
        int[] dp = new int[cost.length + 1];
        dp[0] = 0; //minimum cost of reaching step 0 is 0
        dp[1] = 0; //minimum cost of reaching step 1 is 0
        for (int i = 2; i < dp.length; i++) {
            int takeOneStep = dp[i - 1] + cost[i - 1];
            int takeTwoSteps = dp[i - 2] + cost[i - 2];
            dp[i] = Math.min(takeOneStep, takeTwoSteps);
        }
        return dp[cost.length];
    }


}
