package algorithms.dynamicProgramming.DPForPathsInMatrix;

import java.util.Arrays;

//https://leetcode.com/explore/learn/card/dynamic-programming/634/matrix-path-based-dp/4130/
public class UniquePaths {

    public static void main(String[] args) {

    }


    //Recursive
    public int uniquePathsRecursive(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }

    //Input: m = 3, n = 7
    //Bottom Up approach
    public int uniquePaths(int m, int n) {
        int[][] d = new int[m][n];

        for(int[] arr : d) {
            Arrays.fill(arr, 1);
        }
        for(int col = 1; col < m; ++col) {
            for(int row = 1; row < n; ++row) {
                d[col][row] = d[col - 1][row] + d[col][row - 1];
            }
        }
        return d[m - 1][n - 1];
    }

}
