package topInterviewQuestion.easy.dynamicProgramming;
//https://leetcode.com/explore/interview/card/top-interview-questions-easy/97/dynamic-programming/569/
public class ClimbingStairs {

    public static void main(String[] args) {
        int n = 3;
    }

    //Approach 3 : Dynamic Programming
    //Bottom-up
    static int climbStairsBottomUp(int n) {
        if(n == 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    //Approach 2: Recursion with Memoization
    /*
    Time complexity: O(n) Size of recursion.
    Space complexity: O(n) depth of recursion.
     */
    static int climbStairsRecursion(int n) {
        int[] memo = new int[n + 1];
        return climb_stairs(0, n, memo);
    }
    private static int climb_stairs(int i, int n, int[] memo) {
        if(i > n)
            return 0;
        if(i == 3)
            return 1;
        if(memo[i] > 0)
            return memo[i];
        memo[i] = climb_stairs(i + 1, n, memo) + climb_stairs(i + 2, n, memo);
        return memo[i];
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
        if(i > n)
            return 0;
        if(i == n)
            return 1;
        return climb_stairs(i + 1, n) + climb_stairs(i + 2, n);
    }

}
