package leetcode.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/97/dynamic-programming/569/
public class ClimbingStairs {

    public static void main(String[] args) {
        int n = 3;
        //System.out.println(climbStairsBottomUp(n));
        System.out.println(climbStairsRecursion(5));
        System.out.println(climbStairsRecursionWithMemoization(3));
        System.out.println(climbStairsBottomUp(3));
    }

    //When DP try to use Recursion -> then implement Memoization -> then Bottom Up -> then Bottom Up optimized

    //Recursion: Top-Down approach
    /*
    Time: O(2^n)
    Space: O(n)
     */
    //We start from the top
    static int climbStairsRecursion(int n) { //n represent the current stair
        //Base cases
        if(n == 0) return 0; //We do not have remaining stairs so return 0
        if(n == 1) return 1; //At stair 1, we only 1 unique way to reach the index 0
        if(n == 2) return 2; //At stair 2, we know that we have 2 ways to reaching index 0
        //Recursion
        return climbStairsRecursion(n - 1)
                + climbStairsRecursion(n - 2);
    }

    /*
    Recursion with memoization
    Time: O(n)
    Space: O(n)
     */
    static int climbStairsRecursionWithMemoization(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        //Key: the current stair, Value: the number of ways to go to that stair
        memo.put(1, 1);
        memo.put(2, 2);
        return climbingStairsHelper(n, memo);
    }
    private static int climbingStairsHelper(int n, Map<Integer, Integer> memo) {
        //Base case
        if(n == 0) return 0;
        //Get from cache
        if(memo.containsKey(n)) {
            return memo.get(n);
        }
        //Recursion
        memo.put(n, climbingStairsHelper(n - 1, memo)  //climb 1 step
                + climbingStairsHelper(n - 2, memo)); //climb 2 steps
        //Return from cache
        return memo.get(n);
    }

    //DP Bottom Up
    static int climbStairsBottomUp(int n) {
        if(n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }





    //Approach 1: Brut force
    /*
    Time complexity: O(2²), size of recursion will be 2n
    Space complexity: O(n)
     */
    static int climbStairs(int n) {
        return climb_stairs(0, n);
    }

    private static int climb_stairs(int i, int n) {
        if (i > n)
            return 0;
        if (i == n)
            return 1;
        return climb_stairs(i + 1, n) + climb_stairs(i + 2, n);
    }

}
