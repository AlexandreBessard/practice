package topInterviewQuestion.medium.dynamicProgramming;

import java.util.Arrays;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/111/dynamic-programming/808/
public class UniquePaths {

    public static void main(String[] args) {
        int m = 3, n = 7;
        //System.out.println(uniquePaths(m, n));
        System.out.println(uniquePathsDynamicProgramming(m, n));
        //System.out.println(uniquePathsDynamicProgrammingOptimized(m, n));
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


    //Approach 1: Recursion
    static int uniquePaths(int m, int n) {
        if(m == 1 || n == 1) {
            return 1;
        }
        //Move from cell above
        return uniquePaths(m - 1, n)
                +
                //Move on the left
                uniquePaths(m, n -1);
    }


}
