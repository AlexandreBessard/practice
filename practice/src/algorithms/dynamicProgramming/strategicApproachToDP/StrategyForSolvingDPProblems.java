package algorithms.dynamicProgramming.strategicApproachToDP;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/learn/card/dynamic-programming/631/strategy-for-solving-dp-problems/4096/
public class StrategyForSolvingDPProblems {

    public static void main(String[] args) {
        int stairs = 4;
        System.out.println(climbStairs(stairs));
        System.out.println(climbStairsMemo(stairs));
        System.out.println(climbStairsBottomUp(stairs));
    }

    /*
    Time complexity O(2n) -> without memoization because every call to dp
    creates 2 more calls to dp.
     */
    public static int climbStairs(int n) {
        return dp(n);
    }
    // A function that represents the answer to the problem for a given state
    private static int dp(int i) {
        if(i <= 2) // Base case
            return i;
        return dp(i - 1) + dp(i - 2); //Recurrence relation
    }



    /*
    Time complexity: O(n)
     */
    //------------------ Memoization Recursion
    private static Map<Integer, Integer> memo = new HashMap<>();
    private static int dpMemo(int i) {
        if(i <= 2)
            return i;
        // Instead of just returning dp(i - 1) + dp(i - 2), calculate it once and then
        // store it inside a hashmap to refer to in the future
        if( ! memo.containsKey(i))
            memo.put(i, dp(i -1) + dp(i - 2));

        return memo.get(i);
    }
    public static int climbStairsMemo(int n) {
        return dp(n);
    }




    //--------------------------------BOTTOM-UP
    public static int climbStairsBottomUp(int n) {
        if (n == 1) return 1;

        // An array that represents the answer to the problem for a given state
        int[] dp = new int[n + 1];
        dp[1] = 1; // Base cases
        dp[2] = 2; // Base cases
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // Recurrence relation
        }
        return dp[n];
    }


}
