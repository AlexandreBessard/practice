package algorithms.dynamicProgramming.strategicApproachToDP.Example198;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/learn/card/dynamic-programming/631/strategy-for-solving-dp-problems/4041/
public class NthTribonacciNumber {

    public static void main(String[] args) {
        System.out.println(NthTribonacciNumberBottomUp(4));
        System.out.println(NthTribonacciNumberTopDown(4));
    }

    //Top down
    static Map<Integer, Integer> memo = new HashMap<>();
    static int NthTribonacciNumberTopDown(int n) {
        //Base case
        if(n == 0 || n == 1) {
            return n;
        }
        if(n < 0) {
            return 0;
        }
        //Get from the cache
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        //Recursion
        memo.put(n,
                NthTribonacciNumberTopDown(n - 1)
                        + NthTribonacciNumberTopDown(n - 2)
                        + NthTribonacciNumberTopDown(n - 3)
        );
        return memo.get(n);
    }

    //Bottom Up
    static int NthTribonacciNumberBottomUp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = dp[0] + dp[1];
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }
}
