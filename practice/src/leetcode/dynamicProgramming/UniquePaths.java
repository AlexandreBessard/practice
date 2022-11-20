package leetcode.dynamicProgramming;

import java.util.Arrays;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/111/dynamic-programming/808/
public class UniquePaths {

    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println(uniquePaths(m, n));
        System.out.println(uniquePathsMemoization(m, n));
        //System.out.println(uniquePathsDynamicProgrammingOptimized(m, n));
    }

    //Approach 1: Recursion
    //Correction: https://leetcode.com/problems/unique-paths/discuss/182143/Recursive-memoization-and-dynamic-programming-solutions
    /*
    m = 3, n = 7
      7, 6, 5, 3, 3, 2, 1
    3
    2
    1                   x
     */
    public static int uniquePaths(int m, int n) {
        return helper(m - 1, n - 1); //O-indexed based
    }
    static int helper(int m, int n) { //start at 2 and 6
        if (m < 0 || n < 0) { //Base case 1, Out of bound
            return 0;
        } else if (m == 0 || n == 0) { //Base case 2
            return 1;
        } else {
            return helper(m - 1, n) + helper(m, n - 1);
        }
    }

    //Correction youtube: https://www.youtube.com/watch?v=IlEsdxuD4lY&ab_channel=NeetCode
    public static int uniquePathsMemoization(int m, int n) {
        return uniquePathsHelper(m - 1, n - 1, new int[n][m]);
    }

    private static int uniquePathsHelper(int m, int n, int[][] memo) {
        if (m < 0 || n < 0) {
            return 0;
        } else if (m == 0 || n == 0) {
            return 1;
        } else if (memo[n][m] > 0) {
            return memo[n][m];
        } else {
            memo[n][m] = uniquePathsHelper(m - 1, n, memo) + uniquePathsHelper(m, n - 1, memo);
            return memo[n][m];
        }
    }


    //Approach 3: Optimized Space complexity: need to keep the col above only.
    //Space complexity: O(n)
    static int uniquePathsDynamicProgrammingOptimized(int row, int col) {
        int[] dp = new int[col];
        dp[0] = 1;
        for(int r = 0; r < row; r ++) {
            for(int c = 1; c < col; c++) {
                dp[c] += dp[c - 1];
            }
        }
        return dp[col - 1];
    }


    //Approach 2: Dynamic Programming (Bottom Up)
    /*
    Time complexity: O(N x M)
    Space complexity: O(N x M)
     */
    static int uniquePathsDynamicProgramming(int m, int n) {
        int[][] d = new int[m][n];
        for(int[] arr : d) {
            Arrays.fill(arr, 1);
        }
        for(int row = 1; row < m; row++) {
            for(int col = 1; col < n; col++) {
                d[row][col] =
                        d[row - 1][col] // Path from above (means bottom)
                        + d[row][col - 1]; // Path from the left (means right)
            }
        }
        return d[m - 1][n - 1];
    }
}
